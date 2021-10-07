<#include "base.ftl">
<#macro title>Movie searcher - Login</#macro>
<#macro content>
    <form action="/signUp" method="post">

        <label for="username">username:</label>
        <input id="username" name="username" type="text"/>
        <br>

        <label for="email">email:</label>
        <input id="email" name="email" type="email"/>
        <br>

        <label for="firstName">first name:</label>
        <input id="firstName" name="firstName" type="text"/>
        <br>
        <label for="lastName">last name:</label>
        <input id="lastName" name="lastName" type="text"/>
        <br>

        <label for="password">password:</label>
        <input id="password" name="password" type="password">
        <br>

        <label for="dateOfBirth">Date of birth</label>
        <input id="dateOfBirth" name="dateOfBirth" type="date"/>
        <input type="submit" value="Log in">
    </form>
</#macro>