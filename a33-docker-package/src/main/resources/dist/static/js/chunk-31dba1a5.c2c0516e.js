(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-31dba1a5"],{"371d":function(e,t,a){"use strict";a.r(t);a("b0c0");var l=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-container"},[t("el-form",{ref:"form",attrs:{model:e.form,"label-width":"120px"}},[t("el-form-item",{attrs:{label:"Activity name"}},[t("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),t("el-form-item",{attrs:{label:"Activity zone"}},[t("el-select",{attrs:{placeholder:"please select your zone"},model:{value:e.form.region,callback:function(t){e.$set(e.form,"region",t)},expression:"form.region"}},[t("el-option",{attrs:{label:"Zone one",value:"shanghai"}}),t("el-option",{attrs:{label:"Zone two",value:"beijing"}})],1)],1),t("el-form-item",{attrs:{label:"Activity time"}},[t("el-col",{attrs:{span:11}},[t("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"Pick a date"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1),t("el-col",{staticClass:"line",attrs:{span:2}},[e._v("-")]),t("el-col",{attrs:{span:11}},[t("el-time-picker",{staticStyle:{width:"100%"},attrs:{type:"fixed-time",placeholder:"Pick a time"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),t("el-form-item",{attrs:{label:"Instant delivery"}},[t("el-switch",{model:{value:e.form.delivery,callback:function(t){e.$set(e.form,"delivery",t)},expression:"form.delivery"}})],1),t("el-form-item",{attrs:{label:"Activity type"}},[t("el-checkbox-group",{model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},[t("el-checkbox",{attrs:{label:"Online activities",name:"type"}}),t("el-checkbox",{attrs:{label:"Promotion activities",name:"type"}}),t("el-checkbox",{attrs:{label:"Offline activities",name:"type"}}),t("el-checkbox",{attrs:{label:"Simple brand exposure",name:"type"}})],1)],1),t("el-form-item",{attrs:{label:"Resources"}},[t("el-radio-group",{model:{value:e.form.resource,callback:function(t){e.$set(e.form,"resource",t)},expression:"form.resource"}},[t("el-radio",{attrs:{label:"Sponsor"}}),t("el-radio",{attrs:{label:"Venue"}})],1)],1),t("el-form-item",{attrs:{label:"Activity form"}},[t("el-input",{attrs:{type:"textarea"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("Create")]),t("el-button",{on:{click:e.onCancel}},[e._v("Cancel")])],1)],1)],1)},o=[],r={data:function(){return{form:{name:"",region:"",date1:"",date2:"",delivery:!1,type:[],resource:"",desc:""}}},methods:{onSubmit:function(){this.$message("submit!")},onCancel:function(){this.$message({message:"cancel!",type:"warning"})}}},i=r,n=(a("fe80"),a("2877")),s=Object(n["a"])(i,l,o,!1,null,"32c0383a",null);t["default"]=s.exports},"51e3":function(e,t,a){},fe80:function(e,t,a){"use strict";a("51e3")}}]);