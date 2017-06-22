"use strict";

import React, {Component} from 'react';

import {
  Row,
  Col,
  Form,
  Checkbox,
  FormGroup,
  ButtonGroup,
  ButtonToolbar,
  Button,
  Label,
  Modal,
  ControlLabel,
  FormControl,
  Pagination,
  Panel,
  Grid
} from 'react-bootstrap';
import FixedDataTable from 'fixed-data-table';
import withViewport from '../withViewport';
import querystring from 'querystring';
import {browserHistory} from 'react-router';
import PromiseRequest from '../../data/PromiseRequest';
import Confirmbox from '../Confirmbox/Confirmbox';
import cookieUtils from '../Utils/cookieUtils';
import Alertbox from '../Alertbox/Alertbox';
import DateTimeField from 'react-bootstrap-datetimepicker';
import DateUtil from '../DateUtil/DateUtil';
import SystemConfig from '../../constants/SystemConfig';

var ReactDOM = require('react-dom');

const {Table, Column, Cell} = FixedDataTable;

const TextCell = ({rowIndex, data, col, ...props}) => (
  <Cell {...props}>
    {data[rowIndex][col]}
  </Cell>
);

var divStyle = {
  paddingTop: '20px'
};




var currentTableName, currentTableType;

class List extends Component {

  constructor(props, context) {
    super(props, context);
    let listid = "";
    var stateobj = this.state;
    currentTableName = props.tableName;
    currentTableType = props.tableType;
    this.state = {
      inputs: [],
      listconfig: JSON.parse(props.params),
      serviceid: props.serviceid,
      compid: props.compid,
      confirmBoxOk: "提交",
      confirmBoxFunc: function () {
        alert(1);
        console.error(stateobj);
      },
      formData: {},
      queryForm: {}
    };
  }

  static contextTypes = {
    showClose: React.PropTypes.bool,
    closeCallBack: React.PropTypes.func,
    confirmBoxHead: React.PropTypes.string,
    confirmBoxOk: React.PropTypes.string
  };

  setProperty(e) {
    this.state.queryForm[e.currentTarget.name] = e.currentTarget.value
    this.setState({queryForm: this.state.queryForm});
  }

  setFormValue(flag, e) {
    if(flag == "search"){
      this.state.queryForm[e.currentTarget.name] = e.currentTarget.value
      this.setState({queryForm: this.state.queryForm});
    }else{
      var fd = this.state.formData;
      fd[e.currentTarget.name] = e.currentTarget.value;
      this.setState({
        formData: fd
      });
    }
  }


  setFormDate(name, flag, val) {
    if(flag == "search"){
      var fd = this.state.queryForm;
      fd[name] = DateUtil.numberFormatDate(val, "YYYY-MM-DD HH:mm:ss");
      this.setState({
        queryForm: fd
      });
    }else{
      var fd = this.state.formData;
      fd[name] = DateUtil.numberFormatDate(val, "YYYY-MM-DD HH:mm:ss");
      this.setState({
        formData: fd
      });
    }

  }

  chargeok(e) {
    let actionurl = "/service/" + this.state.serviceid;
    var postData = querystring.stringify(this.getFormData());
    this.postData(actionurl, postData);
  }

  postData(url, val) {
    let that = this;
    PromiseRequest({
      method: 'POST',
      host : SystemConfig.devHost,
      port: SystemConfig.devPort,
      path: url,
      postData: val
    }).then(function (data) {
      let resultJson = JSON.parse(data.body);
      if (resultJson.retcode != 200) {
        alert(resultJson.msg);
      } else {
        that.queryCall(that.getQueryFormData());
      }
      that.onConfirmClose();
    });
  }

  getQueryFormData(){
    var current = this.state.queryForm;
    current["type"] = currentTableType;
    return current;
  }

  getFormData() {
    var current = this.state.formData;
    current["type"] = currentTableType;
    return current;
  }

  onConfirmClose() {
    var currentInnerViewId = this.state.currentInnerViewId;
    let that = this;
    var map = {};
    map[currentInnerViewId] = false;
    map["showDetailViewFlag"] = false;
    that.setState(map);
    this.state.formData = {};
  }

  collectFormCheckData() {
    let that = this;
    that.collectFormData("SELECT");
  }


  componentWillMount() {
    this.queryCall2(this, querystring.stringify(
      {}
    ));
    this.collectFormData()
  }

  queryCall2(that, postData) {
    PromiseRequest({
      method: 'GET',
      host : SystemConfig.devHost,
      port: SystemConfig.devPort,
      path: '/component/config/' + this.state.compid,
    }).then(function (data) {
      let resultJson = JSON.parse(data.body);
      that.setState({inputs: resultJson.service.in,
        listconfig: resultJson.comp.listconfig
      });

    });
  }

  collectFormData(e) {
    this.state.type = e;
    this.queryCall(this.getQueryFormData());

  }

  queryCall(pdata) {
    let that = this;
    PromiseRequest({
      method: 'POST',
      host : SystemConfig.devHost,
      port: SystemConfig.devPort,
      path: '/service/' + this.getQureyCallServiceId(),
      postData: querystring.stringify(pdata)
    }).then(function (data) {
      let resultJson = JSON.parse(data.body);
      that.state.totalSize = resultJson.totalrecords;
      that.state.listdata = resultJson;
      that.state.formData = {};
      that.setState({});
      if (resultJson.listsize == 0) {
        that.setState({
          isShow: true,
          alertBoxType: 'danger',
          message: '查无数据'
        })
      }
    });
  }

  getQureyCallServiceId() {
    var qs = this.state.queryCallServiceid;
    if (!qs) {
      qs = this.state.serviceid;
      this.setState({"queryCallServiceid": qs});
    }
    return qs;
  }


  operate(op, e) {
    var mtd = op['function'];
    this.initOpServiceId(op);
    if ("add" == mtd) {
      this.state.formData = {};
    } else if ("edit" == mtd) {
      if (this.isEmpty(this.state.formData)) {
        this.showAlertMsg("请先选择要修改的项");
        return;
      }
    } else if ("delete" == mtd) {
      if (this.isEmpty(this.state.formData)) {
        this.showAlertMsg("请选择要删除的项");
        return;
      }
      this.showConfirmWindows("删除记录", "删除", "是否要删除选定模板？")
      return;
    }
    this.initField(op.name + currentTableName, op.serviceid);
  }

  isEmpty(obj) {
    for (var name in obj) {
      return false;
    }
    return true;
  }

  initOpServiceId(op) {
    let that = this;
    var serviceid = op.serviceid;
    var opid = op.id;
    if (!opid) {
      opid = op.serviceid;
    }
    that.setState({
      opid: opid,
      hideShowOk: false,
      serviceid: serviceid
    });
  }

  initField(headTile, serviceid) {
    let that = this;
    that.setState({
      confirmBoxIsShow: true,
      currentInnerViewId: "confirmBoxIsShow",
      confirmBoxText: '',
      confirmBoxHead: headTile,
      confirmBoxOk: '确定',
      confirmBoxFunc: function () {
        that.chargeok("UPDATE");
      }
    });
    let rurl = '/resource/service/' + serviceid + "?tableType=" + currentTableType;
    PromiseRequest({
      method: 'GET',
      host : SystemConfig.devHost,
      port: SystemConfig.devPort,
      path: rurl
    }).then(function (data) {
      let resultJson = JSON.parse(data.body);
      that.setState({confirmBoxIsShow: true});
      let tmp = {};
      tmp[that.state.serviceid] = resultJson.in;
      that.setOptionsState(resultJson.in);
      that.setState({confirmBoxIsShow: true});
      that.setState(tmp);
      that.forceUpdate();
    });
  }

  setOptionsState(array){
    let options = {};
    for(var i in array){
      var optionList = array[i].optionlist;
      if(optionList.length > 0){
        var column = array[i].column;
        options[column] = optionList;
      }
    }
    this.setState({"selectOptions": options});
  }

  showAlertMsg(msg) {
    let that = this;
    that.setState({
      alertBoxIsShow: true,
      currentInnerViewId: "alertBoxIsShow",
      alertBoxType: "warning",//success
      onAlertClose: function () {
        that.setState({
          alertBoxIsShow: false
        });
      },
      alertBoxText: msg
    });
  }

  onAlertClose() {
    this.setState({
      alertBoxIsShow: false
    });
  }

  showConfirmWindows(title, okLabel, msg) {
    let that = this;
    that.setState({
      showAdminApproveDetailView: true,
      comboxTitle: title,
      confirmBoxOk: okLabel,
      confirmMsg: msg,
      currentInnerViewId: "showAdminApproveDetailView",
      confirmBoxFunc: function () {
        that.chargeok("DELETE");
      },
      alertBoxText: msg
    });
  }


  selectPage(pageNo) {
    this.state.pageNo = pageNo;
    var fd = this.getQueryFormData();
    fd["pagenum"] = pageNo;
    this.queryCall(fd);
  }

  getCheckResult(rowData, e) {
    var copy = Object.assign({}, rowData);
    this.state.formData = copy;
  }

  showDetailView(rowData) {
    var currentState = this.state;
    currentState["formData"] = rowData;
    currentState["hideShowOk"] = true;
    currentState["showDetailViewFlag"] = true;
    this.setState(currentState);
    this.initField("查看详情", this.state.compid);
  }


  getField(field, flag) {
    if (this.state.showDetailViewFlag) {
      return <Cell>{this.state.formData[field.column]}</Cell>
    }
    if (field.max > 100) {
      var hgt = "80px";
      if(field.max > 1000){
        hgt = "400px";
      }else if(field.max > 300){
        hgt = "200px";
      }
      return <FormControl componentClass="textarea" className="input-group" style={{height: hgt}}
                          name={field.column} value={this.state.formData[field.column]}
                          placeholder={"请输入" + (field.title)}
                          onChange={this.setFormValue.bind(this, flag)}/>;
    } else if (field.type == "datetime" || field.type == "date") {
      var formDate = this.state.formData[field.column];
      if (formDate == undefined) {
        formDate = '';
      }
      var format;
      if (field.type == "datetime"){
        format = "YYYY-MM-DD HH:mm:ss";
      }else{
        format = "YYYY-MM-DD";
      }
      return <DateTimeField inputFormat={format}
                            name={field.column} defaultText={formDate}
                            placeholder={"请输入" + (field.text || field.comments || field.title)}
                            onChange={this.setFormDate.bind(this, field.name, flag)} mode={field.type} />;
    } else if(field.type == "select" ){
      return this.getSelect(field, flag);
    }else {
      return <FormControl type="text" className="input-group" name={field.column}
                          value={this.state.formData[field.column]}
                          placeholder={"请输入" + (field.title)}
                          onChange={this.setFormValue.bind(this, flag)}/>;
    }
  }

  getSearchField(){
    var fields = this.state.inputs;
    var size = fields.length;
    var maxColumnSize = 2;
    var rows = "";
    for(var i = 0; i < size; i = i + maxColumnSize)
    {
      var columns = "";
      for(var j = i; j < maxColumnSize && j < size; j++)
      {
        var tmp = this.getSingleColumn(fields[j]);
        columns = columns + tmp;
      }
      rows = rows + <Row> + {columns} + </Row>;
    }
    return rows;
  }


  getSingleColumn(field){
    var col = "";
    col = col + <Col align="right" hidden={field.hidden} componentClass={ControlLabel} md={1}>
        {field.title}:
    </Col>;
    col = col + <Col align="right" hidden={field.hidden} md={2}>
      {this.getField(field)}
    </Col>;
    return col;
  }


  getSelect(field, type){
    var dictOptions = field.optionlist;
    var subSelectName = field.subcol;
    var options = [<option/>];
    for(var i in dictOptions){
      options.push(<option key={i} value={dictOptions[i].item} id={dictOptions[i].optionName}>{dictOptions[i].caption}</option>);
    }
    return <FormControl componentClass="select" className="input-group" name={field.column}
                        value={this.state.formData[field.column + "_r"]}
                        ref={field.column}
                        placeholder={"请输入" + (field.text || field.comments || field.title)}
                        onChange={this.setSelectFormData.bind(this, type, subSelectName)}>
      {options}
    </FormControl>;
  }

  setSelectFormData(type, subSelectName, e){
    if('search' == type){
      var fd = this.state.queryForm;
      fd[e.currentTarget.name] = e.currentTarget.value;
      fd[e.currentTarget.name + "_r"] = e.currentTarget.value;
      this.setState({queryForm: fd});
    }else{
      var fd = this.state.formData;
      fd[e.currentTarget.name] = e.currentTarget.value;
      fd[e.currentTarget.name + "_r"] = e.currentTarget.value;
      this.setState({
        formData: fd
      });
      if(subSelectName != ""){
        var currentSelectVal = e.currentTarget.value;
        var subSelect = ReactDOM.findDOMNode(this.refs[subSelectName]);
        subSelect.value = null;
        var subSelectOptions = ReactDOM.findDOMNode(subSelect).options;
        for(var i in subSelectOptions){
          var option = subSelectOptions[i];
          if(option.id && option.id.startsWith(currentSelectVal + "_")){
            option.hidden = false;
          }else{
            option.hidden = true;
          }
        }
      }
    }
  }


  render() {
    let {listconfig, listdata} = this.state;
    let {width, height} = this.props.viewport;
    let that = this;

    let CheckBoxCell = ({rowIndex, data, col, ...props}) => (
      <Cell {...props}>
        <input type="radio" name="radio" checked={data[rowIndex].selected}
               onChange={this.getCheckResult.bind(this, data[rowIndex])}/>
      </Cell>
    );

    let OperionCell = ({rowIndex, data, col, ...props}) => (
      <Cell {...props}>
        <Button type="button" className="btn btn-primary btn-sm"
                onClick={this.showDetailView.bind(this, data[rowIndex])}>查看
        </Button>
      </Cell>
    );

    return (
      <div>
        {

          <Panel header={currentTableName} bsStyle="info">
            <Row className="show-grid">
              {
                this.state.inputs.map((field,index) => (

                  <div key={index}>
                    <Col align="right" hidden={field.hidden} md={1} componentClass={ControlLabel}>
                      {field.title}:
                    </Col>
                    <Col hidden={field.hidden} md={3}>
                      {that.getField(field, 'search')}
                    </Col>
                  </div>

                ))
              }

            </Row>
            <Col md={12}>

              <Button bsStyle="primary" onClick={this.collectFormCheckData.bind(this)}>查询</Button>
              {

                listconfig.ops.map((op, index) => (
                  <Button key={index} bsStyle="primary" opid={op.opid} serviceid={op.serviceid}
                          onClick={that.operate.bind(this, op)}>{op.name}</Button>
                ))
              }

            </Col>
            <div>
              {this.state.listdata && this.state.listdata.columndesc ?
                <Table
                  rowHeight={50}
                  headerHeight={50}
                  rowsCount={this.state.listdata.listsize}
                  width={width > 980 ? width - 300 : width - 148}
                  height={500}
                  {...this.props}>
                  <Column
                    cell={<CheckBoxCell data={listdata.crosstable} col=""/> }
                    fixed={true}
                    width={50}
                  />
                  {this.state.listdata.columndesc.map((column, index) => (
                    !column.hidden ?
                    <Column key={index}
                      header={<Cell>{column.title}</Cell>}
                      cell={<TextCell data={listdata.crosstable} col={column.column}/>}
                      width={(width - 300 - 50 - 65) / this.state.listdata.showingColumnsSize}
                    /> : ""
                  ))}
                  <Column header="操作"
                          cell={<OperionCell data={listdata.crosstable} col=""/>}
                          width={65}
                  />
                </Table> : ""}


              <Pagination prev
                          next
                          first
                          last
                          ellipsis
                          boundaryLinks
                          items={ Math.ceil(this.state.totalSize / 20) }
                          maxButtons={20}
                          activePage={this.state.pageNo}
                          onSelect={this.selectPage.bind(this)}/>

            </div>
          </Panel>


        }

        {this.state.confirmBoxIsShow ?
          <Confirmbox showClose={true} confirmBoxHead={this.state.confirmBoxHead} confirmBoxOk={this.state.confirmBoxOk}
                      hideShowOk={this.state.hideShowOk}
                      confirmBoxFunc={this.state.confirmBoxFunc} closeCallBack={this.onConfirmClose.bind(this)}>
            <span style={divStyle.ft16}>{this.state.confirmBoxText}</span>
            <form name="redeemQueryForm" className="confirmBoxRow">
              <Row className="show-grid">
                {
                  that.state[that.state.serviceid] ?
                    that.state[that.state.serviceid].map((field, index) => (
                      <Col md={12} key={index}>
                        <Col md={2} hidden={field.hidden} componentClass={ControlLabel}>
                          {(field.text || field.comments || field.title)}:
                        </Col>
                        <Col md={10} hidden={field.hidden}>
                          {that.getField(field)}
                        </Col>
                      </Col>
                    )) : ""
                }

              </Row>
            </form>
          </Confirmbox> : ''}

        {this.state.alertBoxIsShow ?
          <Alertbox showClose={true} type={this.state.alertBoxType} closeCallBack={this.onConfirmClose.bind(this)}>
            <span style={divStyle.ft16}>{this.state.alertBoxText}</span>
          </Alertbox> : ''}

        {this.state.showAdminApproveDetailView ?
          <Confirmbox showClose={true} confirmBoxHead={this.state.comboxTitle}
                      confirmBoxFunc={this.state.confirmBoxFunc}
                      closeCallBack={this.onConfirmClose.bind(this)} confirmBoxOk={this.state.confirmBoxOk}>
            <span style={divStyle.ft16}>{this.state.confirmMsg}</span>
          </Confirmbox> : ''}


      </div>
    )
  }

}

export default withViewport(List);
