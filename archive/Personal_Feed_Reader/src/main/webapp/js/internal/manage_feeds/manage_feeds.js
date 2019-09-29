var manage_feeds_table;
var feed_map;

function setupManageFeedsTable(){
	
	$.ajax({
		dataType : "json",
		url : 'feeds',
		success : createTable,
		cache: false
	});
	
	function createTable(data){
		var loadedData = [];
		feed_map = data;
	
		if(data!=null){
			$.each(data, function(key, val) {
				loadedData.push(val);
			});
			
		}	
		
		manage_feeds_table = $('#manage_feeds_table').bootstrapTable({
	        idField: 'name',
	      	pagination: true,
	      	search: true,
	      	width : $(document).width()-75,
	      	height: 550,
	      	queryParams: function () {
	      	    return {
	      	        sort: 'name',
	      	        direction: 'desc',
	      	        per_page: 10,
	      	        page: 1
	      	    };
	      	},
	      	search: true,
	        data: loadedData,
	        columns: manage_feeds_table_columns,
	      	onPostBody: function () {
	          	$('#manage_feeds_table').editableTableWidget({editor: $('<textarea>')});
	          	$('#manage_feeds').change(function(event){
          			rowChange(event);
          		});
	          	
	          	if($('#add_new_feed').length==0){
	          		var esc = $.Event("keydown", { keyCode: 27 });
	          		$("body").trigger(esc);
	          		$('<span id="add_new_feed" class="control_link">Add New Feed</span>').insertBefore('.fixed-table-container:first');
	              	$('#add_new_feed').click(function(){
	              		var row = manage_feeds_table.bootstrapTable('insertRow', {index: 0, row: {
	                        "name": "",
	                        "feedUrl": "",
	                        "sourceUrl": "",
	                        "description": "",
	                        "priority": 10,
	                        "sfwLevel": 1,
	                        "type": "Text",
	                        "minComments": 0,
	                        "feedRules": []
	                    }});
	              		row.change(function(event){
	              			rowChange(event);
	              		});
	              	})
	          	}
	        }
	    });
		addOrUpdateFeedOnChange();
	}
}

function rowChange(event){
	var target = $(event.target);
		saveCellUpdate(manage_feeds_table ,manage_feeds_table_columns, target);
		var previousName = previousHack;
		var previousClass = previousClassHack;
		var name = $(event.target).parent().find('.feed_name').text();
		if(previousClass!=null && previousClass.indexOf('feed_name')>-1 && previousName!=null && previousName!=name){
			addFeed(getFeedFromTable(name));
			deleteFeed(previousName);
		} else {
			updateFeed(getFeedFromTable(name));
		}
}

function saveCellUpdate(table, table_columns, target) {
	var isSelect = target.find('option').length > 0;
	target = isSelect ? target.parent() : target;
	var cellIndex = target.index();
	var rowIndex = target.parent().index();
	if(rowIndex<0){
		return;//No idea why I need to do this
	}
	var fld = table_columns[cellIndex].field;
	var val = target.text();
	if (isSelect) {
		indx = target.context.selectedIndex;
		val = $(target.find('option')[target.context.selectedIndex])[0].text;
		$(target).find('option:selected').attr('selected', false);
	}
	table.bootstrapTable('updateCell', ({
		index : rowIndex,
		field : fld,
		value : val
	}));
	$(target.find('option')[target.context.selectedIndex]).attr('selected',
			true)
}

function addOrUpdateFeedOnChange(elementUpdated){
	$('.manage_feeds').change(function(event){
		var target = $(event.target);
		var previousName = previousHack;
		var previousClass = previousClassHack;
		var name = $(event.target).parent().find('.feed_name').text();
		if(name==''){
			name = $(event.target).parent().parent().find('.feed_name').text();
		}
		if(previousClass!=null && previousClass.indexOf('feed_name')>-1 && previousName!=null && !previousName!=name){
			addFeed(getFeedFromTable(name));
			deleteFeed(previousName);
		} else {
			updateFeed(getFeedFromTable(name));
		}
	});
}

function addFeed(feed){
	if(feed==null || feed.name==null || feed.name.trim()==''){
		return;
	}
	feed_map[feed.name] = feed;
	$.ajax({
		dataType : "json",
		cache: false,
		url : 'feeds',
		data : {
			model : JSON.stringify(feed),
			action : 'ADD'
		},
		statusCode: {
			200: function(){				
			}
		}
	});	
}

function getFeedFromTable(name){	
	var row = $('.feed_name:contains(' + name + ')').parent();
	var rowType = row.find('.manage_feeds_type').find(':selected').text();
	if(row.length==1){
		return {
	            "name": row.find('.feed_name').text(),
	            "feedUrl": row.find('.manage_feeds_feed_url').text(),
	            "sourceURL": row.find('.manage_feeds_source_url').text(),
	            "priority": row.find('.manage_feeds_priority').text(),
	            "sfwLevel": row.find('.manage_feeds_sfw').text(),
	            "type": rowType,
	            "minComments": row.find('.manage_feeds_comments').text(),
	            "feedRules": feed_map[name]!=null?feed_map[name].feedRules:[]
		}
	}
	return null;
}

function deleteRow(target){
	var name = $(target).parent().parent().find('.feed_name').text();
	manage_feeds_table.bootstrapTable('remove', {field: 'name', values: [name]});
	deleteFeed(name);
}

function updateFeed(feed){
	if(feed==null || feed.name==null || feed.name.trim()==''){
		return;
	}
	feed_map[feed.name] = feed;
	$.ajax({
		dataType : "json",
		cache: false,
		url : 'feeds',
		data : {
			model : JSON.stringify(feed),
			action : 'UPDATE'
		},
		statusCode: {
			200: function(){
			}
		}
	});	
}

function deleteFeed(name){
	$.ajax({
		dataType : "json",
		cache: false,
		url : 'feeds',
		data : {
			model : name,
			action : 'DELETE'
		},
		statusCode: {
			200: function(){
			}
		}
	});	
}

function displayManageFeedsDialog() {
	$("#manage_feeds_dialog").dialog({
		width : $(window).width()-50,
		height : 700,
		position: { my: 'top', at: 'top+10' }
	}
	);
	$('.ui-add_link_dialog :button').blur();
}