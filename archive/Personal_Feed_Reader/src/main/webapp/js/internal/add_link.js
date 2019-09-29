$(document).ready(function() {
	$('#add_link_submit').click(function(event) {
		addLink();
	});
});

function displayAddLinkDialog() {
	$("#add_link_dialog").dialog({
		width : 700,
		height : 350
	}

	);
	$('.ui-add_link_dialog :button').blur();
}


function addLink() {
	//Get entries from from and validate
	var title = $('#add_title').val().trim();
	if(title==null || title==''){
		alert("Link must have a title");
		return;
	}
	var url = $('#add_url').val().trim();
	if(url==null || url==''){
		alert("Link must have a URL");
		return;
	}
	var group = $('#add_group').find(':selected').text().replace('\u25BC', '');
	var type = $('#add_type').find(':selected').text().replace('\u25BC', '');
	var priority = $('#add_priority').val().trim();
	if(priority==null || priority=='' || !isNumber(priority)){
		alert("Link must have numeric priority");
		return;
	}
	var sfwLevel = $('#add_sfw').val().trim();
	if(sfwLevel==null || sfwLevel=='' || !isNumber(sfwLevel)){
		alert("Link must have numeric SFW level");
		return;
	}
	
	//Create the link
	var link = {
		title: title,
		url: url,
		linkedUrl: url,
		numOfComments: 0,
		source: 'pfr',
		sourceUrl: '#',
		author: '',
		grouping: group,
		type: type,
		added: (new Date()).toISOString(),
		posted: (new Date()).toISOString(),
		priority: priority,
		safeForWorkLevel: sfwLevel,
		notes: ''
	};
	now = new Date();
	link.span = {
			days: 0,
			hours: 0,
			minutes: 0
	}
	
	//Add the link
	linkMap[link.url]=link;
	allKeys.push(link.url);	
	applyFilters();
	applySort();
	loadLinks();
	$.ajax({
		dataType : "json",
		cache: false,
		url : 'links',
		data : {
			'link': JSON.stringify(link),
			'action': 'ADD'
		},
		statusCode: {
			200: function(){				
				
			}
		}
	});
	$("#add_link_dialog").dialog('close');
	$('#add_title').val('');
	$('#add_url').val('');
	$('#add_group').val('perm');
	$('#add_type').val('perm');	
	$('#add_priority').val('')
	$('#add_sfw').val();
}
	
function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}