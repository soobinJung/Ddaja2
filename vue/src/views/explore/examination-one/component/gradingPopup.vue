<template>
    <div>
        <el-dialog 
        :visible.sync="popupVal" 
        title=""
        :before-close="handleClose"> 
        <div class="grading-popup-div1">
            <div class="div1-1">
                <span class="span1">
                    2020년 10월 11일 3회차 모의고사
                </span>
                <span class="span2">
                    데이터 베이스
                </span>
                <span class="span2"> 
                    SCORE : 90 점
                </span>
            </div>
            <div class="div1-2">
                <el-progress :text-inside="true" :stroke-width="20" :percentage="90" status="exception" class="progress"></el-progress>
            </div>
        </div>
        <div class="grading-popup-div2">
            <el-radio-group v-model="quizType" class="div2-1">
                <el-radio :label="3">
                    전체문제 확인
                </el-radio>
                <el-radio :label="6">
                    틀린문제만 확인
                </el-radio>
                <el-radio :label="9">
                    맞은 문제만 확인
                </el-radio>
            </el-radio-group>
        </div>
        <div class="grading-popup-div3" v-for="(quiz) in testJson" :key="quiz.examinationNo" >
            <div class="div3-1">
                <div class="div3-1-1">
                    <span class="span1"> 
                        {{quiz.examinationNo}}. {{quiz.examinationQuestion}}  
                    </span> 
                    <span class="span2">
                        3
                    </span> 
                </div>
                <div class="div3-1-2" >
                    <el-button class="btn1" @click="communityPopupStatus(true)">토 론</el-button>
                </div>
                <div class="div3-1-3" v-for="(answer, index) in quiz.example" :key="answer">
                    <span class="span1" v-if="answer != quiz.examinationAnswer"> 
                        {{index+1}} . {{answer}}
                    </span>
                    <span class="span2" v-else> 
                        {{index+1}} . {{answer}}
                    </span>
                </div>  
            </div>
        </div>
        <span slot="footer"> 
            <el-button type="primary" @click="popupClose(false)">닫 기</el-button> 
        </span>
        </el-dialog>
        <community
        :popup-val="communityPopupVal"
        @close:community="communityPopupStatus"
        />
    </div> 
</template>

<script>
import testJson from '@/assets/jsonFile/subjectExaminationTestJson1'
import community from '@/views/explore/communication'

export default {
    name: 'gradingpopup'
    , data() {
        return { 
            testJson : testJson
            , communityPopupVal : false
            , quizType : 1
        }
    }
    , components : {
        testJson
        , community
    }
    , props: {
        popupVal: {} 
    }
    , watch: { }
    ,methods: { 
        popupClose(val) { 
            // 채점 popup 닫는다.
            this.$emit('close:grading', val) 
        }
        , handleClose(done) {
            // 클릭 이벤트가 popup 벗어나면 확인창.
        this.$confirm('종료 하시겠습니까 ?')
            .then(_ => {  
                this.popupClose(false);
            })
            .catch(_ => {});
        } 
        , communityPopupStatus(val){
            if(val){
                // 토론창 열기 
                this.communityPopupVal = val
            }else{
                // 토론창 닫기
                this.communityPopupVal = val
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap');

.grading-popup-div1{
    width: 100%;
    height: 30px; 
    .div1-1{ 
        width: 100%;
        float: left; 
        span{
            float: left;
            text-align: left; 
            font-family: 'Do Hyeon', sans-serif;
            color: black;
            padding: 0 0 1% 2%;
            width: 100%; 
        }
        .span1{font-size: 40px;}
        .span2{font-size: 32px;} 
    }
    .div1-2{ 
        width: 100%;
        float: left;
        padding: 3% 0 0 5%;
        .progress{
            width: 90%;
        }
    }
}
.grading-popup-div2{
    .div2-1{
        margin: 10px 0 0 0;
        padding: 1% 0 0 5%;
        float: left;
    }
}
.grading-popup-div3{
    .div3-1{  
        width: 93%;
        padding: 2%;
        height: inherit; 
        float: left;
        margin: 3% 3% 3% 3%;
        .div3-1-1{  
            float: left;
            width: 90%;
            padding: 2%;
            font-size: 15px;
            font-weight: bold;
            .span1{
                float: left;
            }
            .span2{
                float: left;
                color: blue;
                padding: 0 0 0 2%;
            }
        }
        .div3-1-3{ 
            float: left;
            width: inherit;
            padding: 2%;
            font-size: 13px;
            font-weight: bold;
            .span1{
                padding-left: 5%;
                float: left;
            }
            .span2{
                float: left;
                padding-left: 5%;
                color: red;
            }
        }
        .div3-1-2{ 
            float: left;
            width: 10%; 
            .btn1{
                float: right;
            }
        }
    }
}
</style>