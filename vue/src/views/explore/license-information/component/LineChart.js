import { Scatter, mixins } from 'vue-chartjs'
const { reactiveProp } = mixins

export default {
extends: Scatter 
    , mixins: [reactiveProp] 
    , props: ['options'] 
    , chartData: {
    type: Object,
    required: true
    }
    , mounted () { 
    // this.renderChart(this.chartData, this.options)
    }
}