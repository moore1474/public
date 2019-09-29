

advanced_options_table_columns = [
{
    field: 'field',
    title: 'Field',
    class: 'feed_rule_column',
    sortable: true,
    formatter: function(val, row, index){    	
		var html = '<select>' 
			 + '<Option>Title</Option>'
			 + '<Option>URL</Option>'
			 + '<Option>Number of Comments</Option>'
			 + '<Option>Source</Option>'
			 + '<Option>Source URL</Option>'
			 + '<Option>Author</Option>'
			 + '<Option>Group</Option>'
			 + '<Option>Type</Option>'
			 + '<Option>Age</Option>'
			 ' <Option>SFW Level</Option>'
			 + '<Option>Page</Option>'
			 + '<Option>Custom JS</Option>'
			 + '</select>';		
		return determineHtmlToReturn(val, html);
	 }
}, {
    field: 'operator',
    title: 'Operator',
    class: 'feed_rule_column',
    width: 75,
    sortable: true,
    formatter: function(val, row, index){        	
		 var html = '<select>' 
			 + '<Option>Contains</Option>'
			 + '<Option>Contains One Of</Option>'
			 + '<Option>Equals</Option>'
			 + '<Option>Equals Ignore Case</Option>'
			 + '<Option>Greater Than</Option>'
			 + '<Option>Less Than</Option>'
			 + '</select>';
		 return determineHtmlToReturn(val, html);
	 }
}, {
    field: 'value',
    title: 'Value',
    class: 'feed_rule_column',
    width: 250,
    sortable: true
},
{
    field: 'fieldToChange',
    title: 'Change Field',
    class: 'feed_rule_column',
    sortable: true,
    formatter: function(val, row, index){    	
    	var html = '<select>' 
			 + '<Option>Title</Option>'
			 + '<Option>URL</Option>'
			 + '<Option>Number of Comments</Option>'
			 + '<Option>Source</Option>'
			 + '<Option>Source URL</Option>'
			 + '<Option>Author</Option>'
			 + '<Option>Group</Option>'
			 + '<Option>Type</Option>'
			 + '<Option>Age</Option>'
			 ' <Option>SFW Level</Option>'
			 + '<Option>Page</Option>'
			 + '<Option>Custom JS</Option>'
			 + '</select>';		
		return determineHtmlToReturn(val, html);
	 }
},
{
    field: 'effectOperator',
    title: 'Effect Operator',
    class: 'feed_rule_column',
    sortable: true,
    formatter: function(val, row, index){    	
    	var html = '<select>' 
			 + '<Option>Add</Option>'
			 + '<Option>Subtract</Option>'
			 + '<Option>Multiply</Option>'
			 + '<Option>Divide</Option>'
			 + '<Option>Move To</Option>'
			 + '<Option>Do Not Add</Option>'
			 + '<Option>Delete</Option>'
			 + '<Option>Permanently Delete</Option>'
			 + '</select>';		
		return determineHtmlToReturn(val, html);
	 }
},
{
    field: 'changeValue',
    title: 'Change Value',
    class: 'feed_rule_column',
    width: 100,
    sortable: true
},
{
	field: 'options',
	 	title: 'Options',
	 	class: 'manage_feeds',
	 	width: 30,
	 	formatter: function(val, row, index){
		 var html = '' 
			 + '<a onclick="deleteAoRow(this)">Delete</a>&nbsp;&nbsp;&nbsp;'
			 + '';
		 return html;
	 }
}
];