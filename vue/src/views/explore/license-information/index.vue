<template>
<div class="main-container">
    <div class="main-title">
        <font class="title-font1">{{ licenseInfo.licenseName }} 자격증 정보</font>
    </div>

    <div class="div1">
        <div class="div1-1">
            <span class="font1">✅ 자격증 과목</span>
        </div>
        <div class="div1-2">
            <div class="div1-2-1"><span class="font1"><i class="el-icon-caret-right"></i>전자 계산기</span></div>
            <div class="div1-2-1"><span class="font1"><i class="el-icon-caret-right"></i>데이터 베이스</span></div>
            <div class="div1-2-1"><span class="font1"><i class="el-icon-caret-right"></i>운영 체제</span></div>
            <div class="div1-2-1"><span class="font1"><i class="el-icon-caret-right"></i>시스템분석 설계</span></div>
            <div class="div1-2-1"><span class="font1"><i class="el-icon-caret-right"></i>정보통신 개론</span></div> 
        </div>
    </div>

    <div class="div2">
        <div class="div2-1">
            <span class="font1">✅ 자격증 일정</span>
        </div>
        <div class="div2-2">
            <el-table
            :data="tableData"
            border
            style="width: 100%">
                <el-table-column
                prop="num"
                label="회차"
                width="100">
                </el-table-column>
                <el-table-column
                prop="name"
                label="시험명"
                min-width="600">
                </el-table-column>
                <el-table-column
                prop="date"
                label="응시일"
                width="250">
                </el-table-column>
                <el-table-column
                prop="dday"
                label="D-day"
                width="150">
                </el-table-column>
            </el-table>
        </div>
    </div>
    
    <div class="div3"> 
        <div class="div3-1">
            <span class="font1">✅ 자격증 합격 현황 : ) </span>
        </div>
        <div class="div3-2"> 
            <line-chart :chart-data="datacollection" ></line-chart>
        </div>
    </div>

    <div class="div4"> 
        <div class="div4-1">
            <span class="font1">✅ 자격증 과락 현황 : (</span>
        </div>
        <div class="div4-2"> 
            <line-chart :chart-data="datacollection" ></line-chart>
        </div>
    </div>

    <div class="div5"> 
        <div class="div5-1">
            <span class="font1">✅ 자격증 합격 기준 </span>
        </div>
        <div class="div5-2">
            <div class="div5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>평균</span><br/>
                <span class="span2">60점 이상</span> 
            </div>
            <div class="div5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>전자 계산기</span><br/>
                <span class="span2">40점 이상</span> 
            </div>
            <div class="div5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>데이터 베이스</span><br/>
                <span class="span2">40점 이상</span>  
            </div>
            <div class="ddiv5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>운영 체제</span><br/>
                <span class="span2">40점 이상</span> 
            </div>
            <div class="div5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>시스템분석 설계</span><br/>
                <span class="span2">40점 이상</span>  
            </div>
            <div class="div5-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>정보통신 개론</span><br/>
                <span class="span2">40점 이상</span> 
            </div> 
        </div>
    </div>

    <div class="div6"> 
        <div class="div6-1">
            <span class="font1">✅ 자격증 응시 자격</span>
        </div>
        <div class="div6-2">
            <div class="div6-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>학력 응시</span><br/>
                <span class="span2">2년제 OR 3년제 관련학과 졸업</span> 
            </div>
            <div class="div6-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>경력 응시</span><br/>
                <span class="span2">3년 이상</span> 
            </div>
            <div class="div6-2-1">
                <span class="span1"><i class="el-icon-caret-right"></i>학점 응시</span><br/>
                <span class="span2">41 학점 보유자</span> 
            </div> 
        </div>
    </div>

</div>
</template>

<script>
import LineChart from './component/LineChart'
export default {
    name: ''
    , components: { 
        LineChart
    }
    , created() {
        this.licenseInfo = this.$session.get('licenseInfo') 
        this.subject = this.licenseInfo.subject
    }
    , mounted () {
        this.fillData()
    }
    , data() {
        return {
            tableData: [ 
                { date: '2011-05-03',  num:'1 회차', dday: 'D DAY - 2', name: '정보처리 산업기사 실기'}
                , { date: '2012-05-03', num:'2 회차', dday: 'D DAY - 5', name: '정보처리 산업기사 필기' }
                , { date: '2013-05-03', num:'3 회차', dday: 'D DAY - 8', name: '정보처리 산업기사 실기' }
                , { date: '2014-05-03', num:'4 회차', dday: 'D DAY - 13', name: '정보처리 산업기사 필기' }
                , { date: '2015-05-03', num:'5 회차', dday: 'D DAY - 15', name: '정보처리 산업기사 실기' }
                , { date: '2016-05-03', num:'6 회차', dday: 'D DAY - 18', name: '정보처리 산업기사 필기' }
                , { date: '2017-05-03', num:'7 회차', dday: 'D DAY - 20', name: '정보처리 산업기사 필기' }
                , { date: '2018-05-03', num:'8 회차', dday: 'D DAY - 25', name: '정보처리 산업기사 실기' } 
            ]
            , search: ''
            , radio1: '1'
            , examPopupStatusVal : false
            , datacollection: null
        }
    }
    , methods: {
        fillData () {
            this.datacollection = {
                labels: ['A1', 'A2'],
                datasets: [
                {
                    label: 'Data One',
                    backgroundColor: '#f87979',
                    data: [1,2,3]
                } 
                ]
            }
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
    float: left;
    background-color: rgb(255, 255, 255);
    .main-title{
        text-align: left;  
        margin-top: 5px;
        margin-bottom: 30px; 
        color: rgb(0, 0, 0);
        margin-left: 1%;
        margin-right: 1%;
        padding: 1.5% 2% 0% 4%;
        .title-font1{
            font-size: 50px;   
            font-family: 'Do Hyeon', sans-serif;
        } 
    } 
    .div1{
        width: 100%; 
        float: left;
        .div1-1{
            padding: 4% 10% 4% 10% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }  
        }
        .div1-2{
            padding: 0 10% 0 10% ;
            width: 100%;
            float: left;
            .div1-2-1{
                text-align: left;
                .font1{
                    font-size: 27px; 
                    display:inline-block;
                    padding: 4px 0 4px 0;
                    font-family: 'Do Hyeon', sans-serif;  
                }
            }
        }
    }
    .div2{
        width: 100%; 
        float: left;
        .div2-1{
            padding: 4% 10% 4% 10% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }  
        }
        .div2-2{
            padding: 0 10% 0 10% ;
            width: 100%; 
        }
    }    
    .div3{
        width: 50%; 
        float: left; 
        .div3-1{
            padding: 10% 10% 10% 20% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }  
        }
        .div3-2{
            padding: 0 0 0 20% ;
            width: 910px; 
        }
    }
    .div4{
        width: 50%; 
        float: left; 
        .div4-1{
            padding: 10% 10% 10% 0% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }  
        }
        .div4-2{
            padding: 0 20% 0 0 ;
            width: 910px; 
        }
    }
    .div5{
        width: 50%; 
        float: left; 
        .div5-1{
            padding: 10% 10% 10% 20% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }  
        }
        .div5-2{
            padding: 0 10% 0 20% ;
            width: 1000px;
            .div5-2-1{
                float: left;
                width: 100%; 
                text-align: left;
                padding: 10px  0 0 0 ;
                .span1{ 
                    font-size: 27px; 
                    display:inline-block;
                    padding: 4px 0 4px 0;
                    font-family: 'Do Hyeon', sans-serif; 
                }
                .span2{
                    font-size: 20px; 
                    font-family: 'Do Hyeon', sans-serif;
                    display:inline-block;
                    padding: 4px 0 4px 30px;
                }
            } 
        }
    }
    .div6{
        width: 50%; 
        float: left; 
        .div6-1{
            padding: 10% 10% 10% 10% ; 
            .font1{
                font-size: 35px;   
                font-family: 'Do Hyeon', sans-serif;
                float: left;
                text-align: left;
            }
            
        }
        .div6-2{
            padding: 0 29% 0 10% ;
            width: 1000px;
            .div6-2-1{
                float: left;
                width: 100%; 
                text-align: left;
                padding: 10px  0 0 0 ;
                .span1{ 
                    font-size: 27px; 
                    display:inline-block;
                    padding: 4px 0 4px 0;
                    font-family: 'Do Hyeon', sans-serif; 
                }
                .span2{
                    font-size: 20px; 
                    font-family: 'Do Hyeon', sans-serif;
                    display:inline-block;
                    padding: 4px 0 4px 30px;
                }
            } 
        }  
    }
} 
</style>
