app = (function(api){
    let _publicFunctions = {};

    let _renderHello = function(data) {
        $(document).ready(() => {
            $("#getreshello").html(data);
        });
    }

    let _renderHelloRemote = function(data) {
        $(document).ready(() => {
            $("#getreshelloremote").html(data);
        });
    }

    let _replaceHtml = function() {
        //local
        //location.replace('https://localhost:5000/services.html');
        location.replace('https://ec2-54-165-150-250.compute-1.amazonaws.com:5000/services.html');
    }

    _publicFunctions.hello = function() {
        api.hello(_renderHello);
    }

    _publicFunctions.helloRemote = function() {
        api.helloRemote(_renderHelloRemote);
    }

    _publicFunctions.login = function(username, password) {
        api.login(username, password, _replaceHtml)
    }

    return _publicFunctions;
})(apiclient);