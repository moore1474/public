function applyFilters(){
		//Get filters and sort to apply
		var group = $('#group_combo').find(':selected').text().replace('\u25BC', '');
		var type = $('#type_combo').find(':selected').text().replace('\u25BC', '');
		var sfwLevelTxt = $('#sfw_combo').find(':selected').text().replace('\u25BC', '');
		var sfwLevel =  Number(sfwLevelTxt);
		
		//Apply Filters
		filteredAndSortedKeys = $.grep(allKeys, function(item, index){
			var shouldStay = true;
			if(linkMap[item].grouping==undefined || linkMap[item].type==undefined){
				return false;
			}
			if(group!='All'){
				shouldStay = shouldStay && linkMap[item].grouping==group;
			}
			if(type!='All'){
				shouldStay = shouldStay && linkMap[item].type==type;
			}
			if(sfwLevelTxt!='All'){
				shouldStay = shouldStay && linkMap[item].safeForWorkLevel<=sfwLevel;
			}
			return shouldStay;
		});
	}

	var sortMap = {
			'Priority': {'attr': 'priority', 'asc':true},
			'Priority Desc': {'attr': 'priority', 'asc':false},
			'Added': {'attr': 'span.getTotalMilliseconds()', 'asc':true},
			'Added Desc': {'attr': 'span.getTotalMilliseconds()', 'asc':false},
			'Comments': {'attr': 'numOfComments', 'asc':true},
			'Comments Desc': {'attr': 'numOfComments', 'asc':false}
	}

	function applySort(){
		var sort = $('#sort_combo').find(':selected').text().replace('\u25BC', '');
		
		var sortFunction = function(attribute, asc){
			filteredAndSortedKeys.sort(function(a, b){
				 pDiff = eval('linkMap[b].' + attribute + '-linkMap[a].' + attribute);
				 pDiff = asc?-pDiff:pDiff;
				 if(pDiff==0){
						var str1=linkMap[b].url;
						var str2=linkMap[a].url;
						pDiff=( ( str1 == str2 ) ? 0 : ( ( str1 > str2 ) ? 1 : -1 ) );
					}
				 return pDiff;
			});
		}
		
		sortFunction(sortMap[sort]['attr'], sortMap[sort]['asc']);		
	}