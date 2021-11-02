<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Sign in</#macro>
<#macro main>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <br>
                <h2>Жанры кино:</h2>
                <ul class="list-unstyled">
                    <#list movieGenres as genre>
                        <li>
                            <a href="/genres?id=${genre.id}" class="link__grey">
                                <strong>${genre.name}</strong>
                            </a>
                        </li>
                    </#list>
                </ul>
        </div>
    </div>
</#macro>