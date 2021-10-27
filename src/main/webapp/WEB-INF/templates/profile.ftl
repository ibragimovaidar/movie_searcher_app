<#include "base.ftl">
<#macro title>Movie searcher - Profile</#macro>
<#macro content>
    <div>
        <p>User profile</p>
        <img src="${imageSrc}" alt="Profile image"/>
        <p>${userDTO.firstName} ${userDTO.lastName}</p>
        <p>${userDTO.username}</p>
    </div>

</#macro>