apiclient = (function() {

    let _publicFunctions = {};


    _publicFunctions.hello = function (callback) {
        return $.get(`/hello`, (data) => {
                    callback(data);
                }).fail();
    }

    _publicFunctions.helloRemote = function (callback) {
        return $.get(`/helloRemote`, (data) => {
                    callback(data);
                }).fail();
    }


    _publicFunctions.login = function (username, password, callback) {
        return $.ajax({
                url: `/login`,
                type: 'POST',
                data: JSON.stringify({
                    username: username,
                    password: password
                }),
                contentType: "application/json",
                success: () => callback(),
                error: () => alert("The username or password are incorrect"),
            });
    };

    return _publicFunctions;
})()