/**
 * 
 * 		Éderson Gervásio
 * 
 */


	/*  ############# Gráfico responsável por apresentar o total de despesas por categoria de despesa  #############	*/

$.ajax({
	
	method: 'GET',
	url: '/graficoDespesaPorCategoria',
    dataType: 'json',
    success: function (data) {
    	// , "#FF0000", "#FF8C00", "#FFFF00", "#00FF7F"
      var donut = Morris.Donut({
	      element: 'chartCategoriaDespesaID',
	      resize: true,
	      colors: ["#00FFFF", "#ADFF2F", "#FFA07A", "#800080", "#B0C4DE", "#00FA9A", "#EE82EE", "#FF0000", "#FF8C00", "#FFFF00", "#00FF7F"],
	      data:  data,
	      formatter: function (x, data) {
	    	  return formatReal(data.value); 
	      },
	      hideHover: 'auto'
    	});
        
      donut.options.data.forEach(function(label, i) {
		    var legendItem = $('<span></span>').text( label['label']).prepend('<br><span>&nbsp;</span>');	 //+ " ( R$ " +formatReal(label['value']) + " )" ).prepend('<br><span>&nbsp;</span>');
		    legendItem.find('span')
		      .css('backgroundColor', donut.options.colors[i])
		      .css('width', '15px')
		      .css('display', 'inline-block')
		      .css('margin', '5px')
		      .css('font-size', '10px');
		    $('#lgCatTotDespID').append(legendItem)
		});
    }
});
				
				
//var ctx = document.getElementById("myChart").getContext('2d');

	/*  ############# Gráfico responsável por apresentar o total de despesas por categoria de despesa  #############	*/

$.ajax({
	
	method: 'GET',
	url: '/graficoTotalDespesaPorMes',
    dataType: 'json',
    success: function (json) {
    	
    	var labels = [], data = [];

        for (var i = 0; i < json.length ; i++){
                
        		labels.push(json[i].mes);
                data.push(json[i].total);
                
        }
    	
        var myChart = new Chart(document.getElementById("myChart").getContext('2d'), {
		  type: 'bar',
			data: {
			    labels: labels,
			    datasets: [{
			      label: 'Valor',
			      data: data,
			      backgroundColor: "rgba(36, 65, 229, 0.85)"
			    }]
			  } ,
			  options: {
				  legend: {display: false},
			      responsive: true,
  				  tooltips: {
  				        callbacks: {
  				            label: function(tooltipItem, data) {
  				            	return formatReal(tooltipItem.yLabel);
  				            }
  				        }
  				    },
	  				  scales: {
	  			        yAxes: [{
	  			          ticks: {
	  			            //beginAtZero: true,
	  			            callback: function(value, index, values) {
	  			              if(parseInt(value) >= 1000){
	  			                return formatReal(value);
	  			              } else {
	  			                return formatReal(value);
	  			              }
	  			            }
	  			          }
	  			        }]
	  			      }
			    }
		});
    }
});

function formatReal(numero) {
    var numero = numero.toFixed(2).split('.');
    numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
}

