var updateComoboLinkMap = JSON.parse('{'+
		'"Favorite": "Favorited",'+
		'"Later": "Later",'+
		'"Much Later": "Much Later",'+
		'"One Day": "One Day",'+
		'"Software": "Software",'+
		'"Career": "Career",'+
		'"Study": "Study",' +
		'"Game": "Game",'+
		'"Mark as Text": "Text",'+
		'"Mark as Image": "Image",'+
		'"Mark as Video": "Video",'+
		'"Mark as Audio": "Audio"'+
	'}');


function decorateLinks(){
	
	//Update display
	$('select').change(function(event){
		var selected = $(event.target).find(':selected')
		var selectedClass = $(event.target).attr('class');
		if(selectedClass!=null && selectedClass.indexOf('linkBottom')<0){
			if(selected.text().indexOf('\u25BC')<0){
				$(event.target).find('.combo_display').text(selected.text() + '\u25BC');
				$(event.target).val('perm');
			}
		}
	});
	
	//Delete link on click
	$('.x').click(function(event){
		linkUpdateAjax($(event.target).parent(), 'grouping','Deleted', function(){});
		$(event.target).parent().remove();
	});
	
	//Update Priority
	$('.update_priority').change(function(event){
		var link = getLinksAssociatedWithElement(event.target);
		var selected = $(event.target).find(':selected').text().replace('\u25BC', '');
		var newPriority = linkMap[link].priority + eval(selected); 
		linkUpdateAjax($(event.target).parent(), 'priority',newPriority, function(){});
	});
	
	//Update SFW Level
	$('.update_sfw').change(function(event){
		var link = getLinksAssociatedWithElement(event.target);
		var selected = $(event.target).find(':selected').text().replace('\u25BC', '');
		var newSfwLevel = linkMap[link].safeForWorkLevel + eval(selected); 
		linkUpdateAjax($(event.target).parent(), 'safeForWorkLevel',newSfwLevel, function(){});
	});
	
	//Update grouping/type
	$('.link_update_combo').change(function(event){
		var link = getLinksAssociatedWithElement(event.target);
		var selected = $(event.target).find(':selected').text().replace('\u25BC', '');
		var newVal = updateComoboLinkMap[selected];
		if(newVal=='Favorited' || newVal=='Later' || newVal=='Current' || newVal=='Much Later' || newVal == 'One Day'
			|| newVal=='Favorite' || newVal=='Software' || newVal=='Career' || newVal=='Game' || newVal == 'Study'){
			linkUpdateAjax($(event.target).parent(), 'grouping',newVal, function(){});
		} else {
			linkUpdateAjax($(event.target).parent(), 'type', newVal, function(){});
		}
		$(event.target).find(':selected').text('\u25BC');
		
	});
}

function linkUpdateAjax(linkElem, linkVar, newVal, doAfterSuccess, loadTheLinks){
	if (typeof(loadTheLinks)==='undefined') loadTheLinks = true;
	var link = getLinksAssociatedWithElement(linkElem);
	linkMap[link][linkVar]=newVal;
	if(loadTheLinks){
		loadLinks();
	}
	$.ajax({
		dataType : "json",
		cache: false,
		url : 'links',
		data : {
			'link': JSON.stringify(linkMap[link]),
			'action': 'UPDATE'
		},
		statusCode: {
			200: function(){
				doAfterSuccess();
			}
		}
	});	
}

function getLinksAssociatedWithElement(elem){
	if($(elem).attr('class')=='link'){
		return  $(elem).find('.comments_link').attr('href');
	}
	return $(elem).parent().find('.comments_link').attr('href');
}