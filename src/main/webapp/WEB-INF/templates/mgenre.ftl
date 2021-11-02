<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Sign in</#macro>
<#macro main>
    <div class="container">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <br>
            <h2>${movieGenreDTO.name}</h2>
            <ul class="list-unstyled">
                <#list movies as movieDTO>
                    <li>
                        <a href="/movie/${movieDTO.id}" class="link__grey">
                            <strong>${movieDTO.name}</strong>
                        </a>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</#macro>