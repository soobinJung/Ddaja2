<template>
<div class="main-container">
    <div class="main-title"><font class="font1">한문제씩 풀기</font><font font class="font2"> - {{ licenseInfo.licenseName }}</font></div>
    <div class="examination-one-div1">  
        <div class="div1-1">
            <el-select v-model="examYearCountTitle" placeholder="Select" class="div1-1-1" size="lazy">
                <el-option
                v-for="item in examYearCount"
                :key="item.examKey"
                :count="item.count"
                :value="item.year">
                <span style="float: left; font-size: 15px; padding:5px">{{ item.year }}</span>
                <span style="float: right; color: #8492a6; font-size: 15px; padding:5px">{{ item.count }}</span>
                </el-option>
            </el-select>
        </div>
        <div class="div1-2">
            <el-radio v-model="radio1" label="1" border>필 기</el-radio>
            <el-radio v-model="radio1" label="2" border>실 기</el-radio>
        </div>
    </div>
    <div  class="examination-one-div2"> 
        <div v-for="vo in subject" :key="vo.subjectid" class="div2-1">
            <div class="div2-1-1"
                :class="colorSelect(vo.subjectid)" 
                @click="examinationPopupStatus(true)"
            >
                <span class="span1">
                    <div class="div2-1-1-1">{{vo.subjectName}} </div> 
                </span>
            </div>
            <div class="div2-1-2">
                <div class="div2-1-2-1"> 
                    <div class="div2-1-2-1-1">
                        <div class="div2-1-2-1-1-1"><span class="span1">전체 진도율 </span></div>
                        <div class="div2-1-2-1-1-2"><el-progress :text-inside="true" :stroke-width="26" :percentage="80"  class="chart"></el-progress></div>
                    </div>
                    <div class="div2-1-2-1-1"> 
                        <div class="div2-1-2-1-1-1"><span class="span1">정답 문제 현황 </span></div>
                        <div class="div2-1-2-1-1-2"><el-progress :text-inside="true" :stroke-width="26" :percentage="30"  class="chart" status="success"></el-progress></div>
                    </div> 
                    <div class="div2-1-2-1-1"> 
                        <div class="div2-1-2-1-1-1"><span class="span1">틀린 문제 현황</span></div>
                        <div class="div2-1-2-1-1-2"><el-progress :text-inside="true" :stroke-width="26" :percentage="40"  class="chart" status="exception"></el-progress></div>
                    </div> 
                </div>
            </div>
        </div>
    </div>
    <examinationPopup
    :popup-val="examinationPopupVal"
    :subjectExamination="subjectExamination"
    @close:examination="examinationPopupStatus"
    @open:grading="gradingPopupStatus"
    />
    <gradingPopup
    :popup-val="gradingPopupVal"
    @close:grading="gradingPopupStatus"
    />
</div>
</template>

<script>
import examinationPopup from './component/examinationPopup'
import gradingPopup from './component/gradingPopup'
import testJson from '../../../assets/jsonFile/subjectExaminationTestJson1'

export default {
    name: 'examination-one'
    , components: {
        testJson
        , examinationPopup
        , gradingPopup
    }
    , data() {
        return {
            attachRed: false
            , examinationPopupVal : false
            , gradingPopupVal : false 
            , licenseInfo: []
            , subject:[]
            , subjectExamination: testJson
            , examYearCount: [
                {
                examKey : 1
                , year: '2015 년 모의고사' 
                , count: '1회차'
                }, 
                {
                examKey : 2   
                , year: '2016 년 모의고사' 
                , count: '2회차'
                }, 
                {
                examKey : 3
                , year: '2016 년 모의고사' 
                , count: '3회차'
                }, 
                {
                examKey : 4
                , year: '2017 년 모의고사' 
                ,  count: '4회차'
                }, 
                {
                examKey : 5
                , year: '2018 년 모의고사' 
                , count: '5회차'
                }, 
                {
                examKey : 6
                , year: '2019 년 모의고사' 
                , count: '6회차'
                }
            ] 
            , examYearCountTitle: '응시 회차수를 선택해주세요.' 
            , radio1: '1'
        }
    }
    , created() {
        this.licenseInfo = this.$session.get('licenseInfo') 
        this.subject = this.licenseInfo.subject
    }
    , methods: {
        examinationPopupStatus (val){
            if(val){
                // 문제 풀이 창 연다
                this.examinationPopupVal = val
            }else{
                // 문제 풀이 창 닫는다
                this.examinationPopupVal = val
            }
        }
        ,gradingPopupStatus(val){
            if(val){
                // 채점 popup 을 연다
                this.examinationPopupStatus(false)
                this.gradingPopupVal = val 
            }else{
                 // 채점 popup 을 닫는다
                this.gradingPopupVal = val
            }
        }
        , colorSelect: function ( colorKey ) {
            return colorKey%7 === 1 ? 'bgc1' : 
                colorKey%7 === 2 ? 'bgc2' : 
                colorKey%7 === 3 ? 'bgc3' : 
                colorKey%7 === 4 ? 'bgc4' : 
                colorKey%7 === 5 ? 'bgc5' : 
                colorKey%7 === 6 ? 'bgc6' :
                colorKey%7 === 7 ? 'bgc7' : 'bgc1'
        }
    }
}
</script>

<style lang="scss" scoped> 
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap'); 
.main-container{
    width: 100%;
    text-align: center; 
    padding: 30px;
    overflow:auto;
    background-color: rgb(255, 255, 255);
    .main-title{
        text-align: left;  
        margin-top: 5px;
        margin-bottom: 30px; 
        color: rgb(0, 0, 0);
        margin-left: 1%;
        margin-right: 1%;
        padding: 1.5% 2% 1.5% 4%;
        .font1{
            font-size: 50px;   
            font-family: 'Do Hyeon', sans-serif;
        }
        .font2{
            font-size: 40px;
            padding: 0 0 0 30px; 
            font-family: 'Do Hyeon', sans-serif;
        }
    }
}
.examination-one-div1{
    width: 90%; 
    height: 80px;
    margin: 0 5% 0 5%;
    .div1-1{  
        float: left;
        padding: 0 0 0 0; 
        width: 73%;
        .div1-1-1{
            float: left;
            width: 1000px;
        }
    }
    .div1-2{  
        float: right;
        padding: 1% 1% 0 10%; 
        width: 27%; 
    }
}
.examination-one-div2{ 
    height: 400px;
    .div2-1{  
        height: 400px;
        .div2-1-1{
            width: 38%;
            height: 350px;
            margin-top: 20px;
            margin-left: 5%;
            margin-right: 10%; 
            float: left;
            cursor: pointer; 
            .span1{ 
                color: aliceblue;
                font-size: 50px;   
                font-family: 'Do Hyeon', sans-serif;
                .div2-1-1-1{
                    padding-top: 20%; 
                    float: left;
                    width: 100%;
                    height: 100%; 
                }
            }
        }
        .div2-1-2{
            width: 38%;
            height: 350px; 
            margin-top: 20px;
            margin-left: 2%; 
            float: left;
            border: 20px solid rgb(255, 255, 170);
            .div2-1-2-1 {
                padding-top: 1%;
                border: 2px solid rgb(255, 255, 44);
                width: 100%;
                height: 100%;
                .div2-1-2-1-1{
                    height: 100px; 
                    width: 100%;
                    float: left; 
                    .div2-1-2-1-1-1{ 
                        float: left;
                        width: 100%;  
                        height: 30px;
                        .span1 {
                            font-weight: bold;
                            padding-left: 3%;
                            float:left;
                            color: rgb(0, 0, 0); 
                        }
                    } 
                    .div2-1-2-1-1-2{ 
                        float: left;
                        width: 100%;  
                        height: 30px;
                        .chart{
                            padding-left: 3%;
                            width: 80%;
                        }
                    }
                }
            }
            
        }
    }
}
.bgc1 { background-color: #ffd19d;}
.bgc2 { background-color: #dd9aff;}
.bgc3 { background-color: #fface0;}
.bgc4 { background-color: #ffdf6b;}
.bgc5 { background-color: #96bb7c;}
.bgc6 { background-color: #ff5200; }
</style>
