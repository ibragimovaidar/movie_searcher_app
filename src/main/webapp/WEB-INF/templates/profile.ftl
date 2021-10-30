<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Profile</#macro>
<#macro main>
    <div class="container">
        <div class="row my-5">
            <div class="col-md-4 col-sm-12">
                <div class="user__info">
                    <img class="rounded-circle" width="192" height="192" src="${imageSrc}" alt="image">
                    <div class="user__info__name">
                        <p class="h3">${userDTO.firstName} ${userDTO.lastName}</p>
                        <p>@${userDTO.username}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-8 col-sm-12 align-self-center">
                <div class="user__info__description">
                    <p class="h3">Bio</p>
                    <p>${userDTO.description}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="reviews__header">
                    <p>Рецензии пользователя</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="container-fluid reviews">
                    <div class="row review__item">
                        <div class="col-md-2 align-self-center">
                            <a href="#" class="review__link">
                                <p class="movie__name">
                                    Movie name
                                </p>
                            </a>
                        </div>
                        <div class="col-md-2 offset-md-3 align-self-center">
                            <p class="movie__name">
                                97/100
                            </p>
                        </div>
                        <div class="col-md-2 offset-md-3 align-self-center">
                            <p class="movie__name">
                                20.07.21 23:21
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>