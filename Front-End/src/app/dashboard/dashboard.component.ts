import {Component, OnInit} from '@angular/core';
import {ChartType} from 'chart.js';
import {MultiDataSet, Label, Colors} from 'ng2-charts';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {

  constructor() {
  }

  public doughnutChartLabels: Label[] = ['Paypal', 'Stripe', 'Cash'];
  public doughnutChartData: MultiDataSet = [
    [55, 25, 20]
  ];
  public doughnutChartColors: Colors[] = [
    {
      backgroundColor: [
        '#111111',
        '#00d25b',
        '#ffab00'
      ]
    }
  ];
  public doughnutChartType: ChartType = 'doughnut';
  public doughnutChartChartPlugins = {
    beforeDraw(chart) {
      const width = chart.chart.width;
      const height = chart.chart.height;
      const ctx = chart.chart.ctx;

      ctx.restore();
      let fontSize = 1;
      ctx.font = fontSize + 'rem sans-serif';
      ctx.textAlign = 'left';
      ctx.textBaseline = 'middle';
      ctx.fillStyle = '#ffffff';

      const text = '$1200';
      const textX = Math.round((width - ctx.measureText(text).width) / 2);
      const textY = height / 2.4;

      ctx.fillText(text, textX, textY);

      ctx.restore();
      fontSize = 0.75;
      ctx.font = fontSize + 'rem sans-serif';
      ctx.textAlign = 'left';
      ctx.textBaseline = 'middle';
      ctx.fillStyle = '#6c7293';

      const texts = 'Total';
      const textsX = Math.round((width - ctx.measureText(text).width) / 1.93);
      const textsY = height / 1.7;

      ctx.fillText(texts, textsX, textsY);
      ctx.save();
    }
  }
  public doughnutChartOptions: any = {
    responsive: true,
    cutoutPercentage: 70,
    maintainAspectRatio: true,
    segmentShowStroke: false,
    elements: {
      arc: {
        borderWidth: 0
      }
    },
    legend: {
      display: false,
    }
  };

  portfolioCarousel = {
    loop: true,
    dots: false,
    margin: 10,
    items: 1,
    nav: true,
    autoplay: true,
    autoplayTimeout: 5500,
    navText: ['<i class=\'mdi mdi-chevron-left\'></i>', '<i class=\'mdi mdi-chevron-right\'></i>']
  }

  mapStyle = {
    sources: {
      world: {
        type: 'geojson',
        data: 'assets/countries.geo.json'
      }
    },
    version: 8,
    layers: [{
      id: 'countries',
      type: 'fill',
      source: 'world',
      layout: {},
      paint: {
        'fill-color': '#ffffff'
      }
    }]
  }

  toggleProBanner(event) {
    event.preventDefault();
    document.querySelector('body').classList.toggle('removeProbanner');
  }

  ngOnInit() {
  }

}
