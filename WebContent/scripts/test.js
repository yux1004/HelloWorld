(function() {

	function init() {
		loadItems();

	}
	
	function loadItems() {
        console.log('loadItem');
        // The request parameters
        var url = './register';
//        var params = 'user_id=' + user_id + '&lat=' + lat + '&lon=' + lng;
        var req = JSON.stringify({});

        // display loading message
        showLoadingMessage('Loading nearby items...');

        // make AJAX call
        ajax('GET', url + '?' + params, req,
            // successful callback
            function(res) {
                var items = JSON.parse(res);
                if (!items || items.length === 0) {
                    showWarningMessage('No Input.');
                } else {
                    listItems(items);
                }
            },
            // failed callback
            function() {
                showErrorMessage('Error get person info.');
            });
    }
	
    /**
     * AJAX helper
     * 
     * @param method -
     *            GET|POST|PUT|DELETE
     * @param url -
     *            API end point
     * @param callback -
     *            This the successful callback
     * @param errorHandler -
     *            This is the failed callback
     */
    function ajax(method, url, data, callback, errorHandler) {
        var xhr = new XMLHttpRequest();

        xhr.open(method, url, true);

        xhr.onload = function() {
        	if (xhr.status === 200) {
        		callback(xhr.responseText);
        	} else {
        		errorHandler();
        	}
        };

        xhr.onerror = function() {
            console.error("The request couldn't be completed.");
            errorHandler();
        };

        if (data === null) {
            xhr.send();
        } else {
            xhr.setRequestHeader("Content-Type",
                "application/json;charset=utf-8");
            xhr.send(data);
        }
    }

	
	// -------------------------------------
	// Create item list
	// -------------------------------------

	/**
	 * List items
	 * 
	 * @param items -
	 *            An array of item JSON objects
	 */
	function listItems(items) {
		// Clear the current results
		var itemList = $('item-list');
		itemList.innerHTML = '';

		for (var i = 0; i < items.length; i++) {
			addItem(itemList, items[i]);
		}
	}

	/**
	 * Add item to the list
	 * 
	 * @param itemList -
	 *            The
	 *            <ul id="item-list">
	 *            tag
	 * @param item -
	 *            The item data (JSON object)
	 */

	
	function addItem(itemList, person) {
		var person_fullname = person.firstName + person.lastName;

		// create the <li> tag and specify the id and class attributes
		var li = $('li', {
			id : 'person-' + person_fullname,
			className : 'person_fullname'
		});

		// set the data attribute
		li.dataset.firstName = person.firstName;
		li.dataset.lastName = person.lastName;
		li.dataset.address1 = person.address1;
		li.dataset.address2 = person.address2;
		li.dataset.city = person.city;
		li.dataset.state = person.state;
		li.dataset.zipCode = person.zipCode;
		li.dataset.country = person.country;
		

		// date
		var date = $('p', {
			className : 'person-date'
		});

		date.innerHTML = person.date.replace(/,/g, '<br/>').replace(/\"/g,
				'');
		li.appendChild(date);
		itemList.appendChild(li);
	}

	init();

})();
