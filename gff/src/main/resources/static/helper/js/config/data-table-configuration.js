 $(function () {
    $('#tableID').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false,
      "language": {
    	  "emptyTable": "Nenhum registro encontrado!",
    	  "infoEmpty": "0 Registros",
    	  "loadingRecords": "Carregando...",
    	  "processing":     "Processando...",
    	  "search":         "Pesquisar:",
    	  "lengthMenu":     "",
    	  "info":   "",
    	    	paginate: {
    	    		first:    '«',
    	    		previous: 'Anterior',
    	            next:     'Próximo',
    	            last:     '»'
    	        },
    	        aria: {
    	            paginate: {
    	            	first:    'Principal',
    	            	previous: 'Anterior',
    	                next:     'Próximo',
    	                last:     'Final'
    	            }
    	        }
    	  },
    })
  });
