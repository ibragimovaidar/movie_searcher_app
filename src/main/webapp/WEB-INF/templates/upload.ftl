<#ftl encoding='UTF-8'>
<html lang="en">
<#include "base.ftl">
<#macro title>Upload file</#macro>

<#macro main>
    <p>Обновить аватарОчку:</p>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <br>
        <input type="submit" value="upload">
    </form>
</#macro>
</html>