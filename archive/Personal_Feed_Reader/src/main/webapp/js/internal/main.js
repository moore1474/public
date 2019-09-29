$(document).ready(onReady);

var page_number = 1;
var num_of_pages;
var allKeys = [];
var filteredAndSortedKeys = [];
var linkMap;

function onReady() {
	$.ajax({
		dataType : "json",
		url : 'links',
		success : loadLinks,
		cache: false
	});

	setupControls();
	setupManageFeedsTable();
	setupAdvancedOptionsTable();
}

function loadLinks(data) {
	refreshLinkMapIfFromAjax(data);
	createLinksInDOM();
	decorateLinks();
	updateLinkCount(filteredAndSortedKeys.length);
	resizeParentIframeIfExists();
}

function refreshLinkMapIfFromAjax(data) {
		if (data != null) {
			linkMap = data;
			allKeys = [];
			$.each(data, function(key, val) {
				val.span = new TimeSpan(now - Date.parse(val.added));
				allKeys.push(key);
			});
		}
		applyFilters();
		applySort();
}

var now = new Date();
function createLinksInDOM() {
	$('.link').not('#link_template').remove();
	$('#page_nav').remove();
	var addPageNav = false;// Only add if there are links
	$.each(getPageOfLinks(page_number),
			function(index, item) {
				addPageNav = true;
				var val = linkMap[item];
				var template = $('#link_template').clone();
				var title = template.find('.title');
				var comments = template.find('.comments_link');
				var sourceLink = template.find('.source_link')
				title.text(val.title);
				title.attr('href', val.linkedUrl);
				comments.text(val.numOfComments + ' comments');
				comments.attr('href', val.url);
				sourceLink.text(val.source);
				sourceLink.attr('href', val.sourceUrl);
				template.removeAttr('id');
				template.appendTo('#root');
				template.find('.priority_combo_display').text(
						val.priority + 'P\u25BC');
				template.find('.sfw_combo_display').text(
						val.safeForWorkLevel + 'S\u25BC');
				
				//Posted
				var timeText = '';
				if(val.span.days>30){
					timeText = Math.trunc(val.span.days/30) + 'mo';
				}
				if(val.span.days>7 && val.span.days<30){
					timeText = Math.trunc(val.span.days/7) + 'w';
				}
				else if(val.span.days>0){
					timeText = val.span.days + 'd';
				} else if(val.span.hours>0) {
					timeText = val.span.hours + 'h';
				} else {
					timeText = val.span.minutes + 'm';
				}
				template.find('.time_posted').text(timeText);
				
				if (linkMap[item].grouping == 'Deleted') {
					template.find('.x').css('visibility', 'hidden');
				}
			});

	// Add page Nav
	if (addPageNav) {
		createPageNav();
	}
}
function resizeParentIframeIfExists(){
	var iFrame = $(parent.document.body).find("iframe[name='cIframe']")
	if(iFrame.length==1){
		var h = $(document.body).css('height');
		$(parent.document.body).css('height', h)
		$(parent.document.body).find('iframe').css('height', h);
	}
}