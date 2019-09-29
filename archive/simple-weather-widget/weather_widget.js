app.directive("weather", function($sce) {

	var YELLOW_PRECIP = 30;
	var RED_PRECIP = 50;
	var desc_symbol_map = {
			'Cloudy': $sce.trustAsHtml('\u2601'),
			'Mostly Cloudy': $sce.trustAsHtml('\u26C5'),
			'Partly Sunny': $sce.trustAsHtml('\u26C5'),
			'Sunny': $sce.trustAsHtml('\u263C'),
			'Mostly Sunny': $sce.trustAsHtml('&#127780'),
			'Partly Cloudy': $sce.trustAsHtml('&#127780'),
			'Mostly Clear': $sce.trustAsHtml('&#9790')
	};
	
	return {
		restrict : 'E',
		scope : {
			tpath : '@',
			hourly : '@',
			daily : '@'
		},
		templateUrl : function(elem, attrs) {
			return attrs.templateurl
		},
		link : function($scope, element, attrs) {
			getTemplateHost($scope, attrs.templateurl);
			
			attrs.$observe("hourly",function(newValue,oldValue) {
				update($scope, attrs);				
			});
			attrs.$observe("daily",function(newValue,oldValue) {
				update($scope, attrs);				
			});
		}
	}
	
	function update($scope, attrs){
		// Parse the hourly JSON
		$scope.hourly_weather = JSON.parse(attrs.hourly);
		
		$scope.daily_weather = JSON.parse(attrs.daily);
		
		add_desc_symbols($scope.hourly_weather,  $sce);
		add_desc_symbols($scope.daily_weather,  $sce);
		
		$scope.rain_today = find_rain_today($scope.hourly_weather);
		
		addRainColorsToDaily($scope.daily_weather);
		getNextWeekend($scope.daily_weather, $scope);
	}

	function find_rain_today(hourly_data) {

		rain_today = [];

		// Find index

		// Create Start and Stop Points for rain		
		var start, start_p, highest_in_set, start_index;
		var find_rain_start = function(start_point){
			for (var i = start_point; i < hourly_data.length; i++) {
				if (precip_as_num(hourly_data[i].precip) >= YELLOW_PRECIP) {
					start = hourly_data[i];
					start_p = precip_as_num(start.precip)
					highest_in_set = start_p;
					start_index = i;
					break;
				}
			}
		};
		find_rain_start(0);
		
		if (start == null) {
			return rain_today;
		}

		var isYellowToRed = function(old_p, new_p) {
			return old_p < RED_PRECIP && new_p >= RED_PRECIP
		};
		var isYellowToNormal = function(old_p, new_p) {
			return old_p < RED_PRECIP && new_p < YELLOW_PRECIP
		};
		var isRedToYellow = function(old_p, new_p) {
			return old_p >= RED_PRECIP && new_p < RED_PRECIP
					&& new_p >= YELLOW_PRECIP
		};
		var isRedToNormal = function(old_p, new_p) {
			return old_p >= RED_PRECIP && new_p < YELLOW_PRECIP
		};

		var no_stop_for_last_pair = false;

		for (var i = start_index; i < hourly_data.length; i++) {

			var current = hourly_data[i];
			var current_p = precip_as_num(current.precip);

			// Check for Stop Point
			if (isYellowToRed(start_p, current_p)
					|| isYellowToNormal(start_p, current_p)
					|| isRedToYellow(start_p, current_p)
					|| isRedToNormal(start_p, current_p)) {

				// Add Start & Stop and start new
				rain_today.push({
					'start' : start.time,
					'end' : current.time,
					'chance' : highest_in_set,
					'color' : highest_in_set > RED_PRECIP ? 'red-precip' : 'yellow-precip'
				});
				find_rain_start(i+1);
				i = start_index;

			} else {
				no_stop_for_last_pair = i == hourly_data.length - 1 ? true
						: false;
				highest_in_set = Math.max(highest_in_set, current_p);
			}
		}

		if (no_stop_for_last_pair) {
			rain_today.push({
				'start' : start.time,
				'end' : 'Uknown',
				'chance' : highest_in_set,
				'color' : highest_in_set > RED_PRECIP ? 'red-precip' : 'yellow-precip'
			});
		}
		return rain_today;
	}

	


	function precip_as_num(str,  $sce) {
		return parseInt(str.replace('%', ''));
	}
	
	
	function add_desc_symbols(data){
		data.forEach(function(item){
			if(desc_symbol_map[item.desc] != null){
				item.desc_sym = desc_symbol_map[item.desc];
			}
			else {
				item.desc_sym = '?';
			}
		});
	}
	
	function addRainColorsToDaily(dailyWeather) {
		dailyWeather.forEach(function(day){
			precipNum = precip_as_num(day.precip);
			day.rainColor = precipNum >= RED_PRECIP ? 'red-precip' : precipNum >= YELLOW_PRECIP ? 'yellow-precip' : 'green-precip';
		});
	}
	
	function getNextWeekend(dailyWeather, scope){
		for(i=dailyWeather.length-1;i>0;i--){
				if(dailyWeather[i].dayOfWeek == 'Sun'){
					scope.nextSunday = dailyWeather[i];
					scope.nextSaturday = dailyWeather[i-1];
					break;
			}
		}
	}
	
	function getTemplateHost(scope, url){
		var start_of_path = url.lastIndexOf('/');
		scope.templateHost = url.substring(0, start_of_path);
	}
	
});