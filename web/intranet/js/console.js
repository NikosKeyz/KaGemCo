function onLogout() {

    deleteCookie();
    window.location = './login.html';

}

function deleteCookie() {
    document.cookie = ';Expires=Thu, 01-Jan-1970 00:00:01 GMT;';
}