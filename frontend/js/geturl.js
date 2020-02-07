// Récupérer l'url
function getUrl(path, withAppPath = true) {
    var protocol = "http";
    var appPath =  withAppPath ? "rest" : "";
    var host = "localhost";
    var port = 8082;
    return protocol + "://" + host + ":" + port + "/" + appPath + path;
}
