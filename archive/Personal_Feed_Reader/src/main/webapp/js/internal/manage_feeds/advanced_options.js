var advanced_options_table;
var feedName;//When the window is showing this is the feed it is being displayed for

function setupAdvancedOptionsTable(){
	advanced_options_table = $('#advanced_options_table').bootstrapTable({
		pagination: true,
      	search: true,
      	width : $(document).width()-200,
      	height: 300,
      	queryParams: function () {
      	    return {
      	        sort: 'field',
      	        direction: 'desc',
      	        per_page: 10,
      	        page: 1
      	    };
      	},
      	search: true,
        data: [],
        columns: advanced_options_table_columns,
      	onPostBody: function () {
          	$('#advanced_options_table').editableTableWidget({editor: $('<textarea>')});
          	if($('#add_new_feed_ao').length==0){
          		$('<span id="add_new_feed_ao" class="control_link">Add New Rule</span>').insertBefore('.fixed-table-container');
              	$('#add_new_feed_ao').click(function(){
              		var esc = $.Event("keydown", { keyCode: 27 });
	          		$("body").trigger(esc);
              		var row = advanced_options_table.bootstrapTable('insertRow', {index: 0, row: {
                        "field": "",
                        "operator": "",
                        "value": "",
                        "fieldToChange": "",
                        "effectOperator":"",
                        "changeValue":""
                    }});
              		
              		row.change(function(event){
              			saveCellUpdate(advanced_options_table ,advanced_options_table_columns, $(event.target));
              		});
              		
              	})
          	}
        }
    });
}

function deleteAoRow(target){
	var name = $(target).parent().parent().remove();
	updateFeedMapFromTable();
	clearTable();
	insertRulesFromFeedMap();
}

function clearTable(){
	advanced_options_table.bootstrapTable('removeAll');
}

function insertRulesFromFeedMap(){
	var rules = feed_map[feedName].feedRules;
	for(i=rules.length-1;i>=0;i--){
		var row = advanced_options_table.bootstrapTable('insertRow', {index: 0, row: rules[i]});
		row.change(function(event){
  			saveCellUpdate(advanced_options_table ,advanced_options_table_columns, $(event.target));
  		});
	}
}

function updateFeedMapFromTable(){
	feed_map[feedName]['feedRules'] = [];
	var feedRuleElements = $('.feed_rule_column');
	for(i=12; i<feedRuleElements.length;i=i+6){
		
		//Fields
		var field = $(feedRuleElements[i]).find("option:selected").text();
		var operator = $(feedRuleElements[i+1]).find("option:selected").text();
		var value = feedRuleElements[i+2].textContent;
		var fieldToChange = $(feedRuleElements[i+3]).find("option:selected").text();
		var effectOperator = $(feedRuleElements[i+4]).find("option:selected").text();
		var changeValue = feedRuleElements[i+5].textContent;
		
		var jsonRule = {'field': field, 'operator': operator, 'value': value, 'fieldToChange':fieldToChange , 'effectOperator':effectOperator ,'changeValue':changeValue};
		feed_map[feedName]['feedRules'] .push(jsonRule);			
	}
}

function displayAdvancedOptionsDialog(target){
	feedName = $(target).parent().parent().find('.feed_name').text();
	if(feedName==null || feedName.trim()==''){
		alert("Feed name must be defined before creating rules");
		return;
	}
	var dialog = $("#advanced_options_dialog").dialog({
		width : $(window).width()-150,
		height : 650,
		position: { my: 'top', at: 'top+30' }
	}
	);
	clearTable();
	insertRulesFromFeedMap();
	
	
	
	//Read all rules, put it in the model, and save before closing
	dialog.on( "dialogbeforeclose", function( event, ui ) {
		updateFeedMapFromTable();	
		updateFeed(feed_map[feedName]);
	});
}