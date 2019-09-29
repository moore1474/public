var determineHtmlToReturn = function(val, html){
	if(val!=null && val.toLowerCase().indexOf('option')>=0){
		return val;
	}
	if(val!=null && val!=''){
		var jHtml = $(html);
		var option = jHtml.children().filter(function(){return $(this).text() == val;})
		option.attr('selected', 'selected');
		html = jHtml[0].outerHTML;	
	}
	return html;
}

manage_feeds_table_columns = [{
    field: 'name',
    title: 'Name',
    class: 'manage_feeds feed_name',
    sortable: true
}, {
    field: 'feedUrl',
    title: 'Feed URL',
    class: 'table_url manage_feeds manage_feeds_feed_url',
    width: 250,
    sortable: true
}, {
    field: 'sourceUrl',
    title: 'Source URL',
    class: 'table_url manage_feeds manage_feeds_source_url',
    width: 250,
    sortable: true
}, {
    field: 'priority',
    title: 'Priority',
    class: 'manage_feeds manage_feeds_priority',
    width: '30px',
    sortable: true
},
{
    field: 'sfwLevel',
    width: '30px',
    title: 'SFW Level',
    class: 'manage_feeds manage_feeds_sfw',
    sortable: true
},
{
    field: 'minComments',
    title: 'Min Comments',
    class: 'manage_feeds manage_feeds_comments',
    width: '30px',
    sortable: true
},
{
	 field: 'type',
	 title: 'Type',
	 class: 'manage_feeds manage_feeds_type',
	 formatter: function(val, row, index){ 		 
		 var html = '<select>' 
			 + '<Option>Text</Option>'
			 + '<Option>Video</Option>'
			 + '</select>';
		 return determineHtmlToReturn(val, html);
	 }
},
{
	field: 'options',
	 	title: 'Options',
	 	class: 'manage_feeds',
	 	formatter: function(val, row, index){
		 var html = '' 
			 + '<a onclick="deleteRow(this)">Delete</a>&nbsp;&nbsp;&nbsp;'
			 + '<a onclick="$(this).parent().trigger(' + "'change'" + ');displayAdvancedOptionsDialog(this)">Advanced Options</a>'
			 + '';
		 return html;
	 }
}
];