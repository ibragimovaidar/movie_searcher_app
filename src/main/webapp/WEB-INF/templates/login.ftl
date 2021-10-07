<#include "base.ftl">
<#macro title>Movie searcher - Login</#macro>
<#macro content>
    <form action="/signIn" method="post">
        <label for="username">Username</label>
        <input id="username" name="username" type="text"/>
        <br>
        <label for="password">Password</label>
        <input id="password" name="password" type="password">
        <br>
        <input type="submit" value="Log in">
    </form>
</#macro>