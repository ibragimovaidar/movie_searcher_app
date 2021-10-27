<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title> ${movieDTO.name} - Shikimori</#macro>
<#macro main>
    <div class="container main__container">
        <div class="row movie__info">
            <div class="col-md-3 movie__info__image">
                <img src="https://avatars.mds.yandex.net/get-kinopoisk-image/1900788/a7184254-8126-4e37-bbca-ebc4ea2e38d5/300x" alt="movie image">
            </div>
            <div class="col-md-7 movie__info__info">
                <div class="container-fluid">
                    <div class="row movie__title">
                        <div class="col-md-12">
                            <h1><strong>${movieDTO.name}</strong></h1>
                        </div>
                    </div>
                    <div class="row movie__info__about">
                        <div class="col-md-12">
                            <p class="h3">О фильме</p>
                            <table class="table table-hover movie__info__about__table">
                                <tr>
                                    <td class="text__grey">Год выпуска</td>
                                    <td>2020</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Страна</td>
                                    <td>${movieDTO.country}</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Жанр</td>
                                    <td >Боевик</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Премьера</td>
                                    <td>13 сентября 2002</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Длительность</td>
                                    <td>164 минуты</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-2 movie__info__info">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 movie__info__info__rating ">
                            <p class="h1 text-center">97/100</p>
                            <a href="#" class="text-dark">
                                <p class="text-center">24 рецензии</p>
                            </a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 movie__info__participants">
                                <span class="text-secondary text-center movie__info__participants__header">
                                    В главных ролях:
                                </span>
                            <ul class="list-unstyled text__grey">
                                <li>
                                    <a href="#" class="link__grey">Дэниэл Крэйг</a>
                                </li>
                                <li>
                                    <a href="#" class="link__grey">Рами Малек</a>
                                </li>
                                <li>
                                    <a href="#" class="link__grey">Леа Сейду</a>
                                </li>
                                <li>
                                    <a href="#" class="link__grey">Лашана Линч</a>
                                </li>
                                <li>
                                    <a href="#" class="link__grey">Бен Уишоу</a>
                                </li>
                                <li>
                                    <a href="#" class="link__grey">Наоми Харрис</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row movie__description">
                <p class="h3">Обзор</p>
                <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Sapiente molestiae doloribus perspiciatis. Sequi, ipsa hic. A debitis quibusdam, incidunt nesciunt, consequuntur laudantium quidem nobis dicta cupiditate id dolorum sequi molestias?</p>
            </div>
        </div>
    </div>
</#macro>