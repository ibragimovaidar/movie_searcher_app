<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title> ${movieDTO.name}</#macro>
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
                                    <td>${movieDTO.dateOfRelease}</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Страна</td>
                                    <td>${movieDTO.country}</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Жанр</td>
                                    <td>${movieDTO.movieGenreName}</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Премьера</td>
                                    <td>${movieDTO.dateOfRelease}</td>
                                </tr>
                                <tr>
                                    <td class="text__grey">Длительность</td>
                                    <td>164 минуты</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <#if authorized??>
                    <div class="movie__info__review_btn__wrapper">
                        <a href="/createReview?movieId=${movieDTO.id}" class="btn ">Написать рецензию</a>
                    </div>
                </#if>
            </div>
            <div class="col-md-2 movie__info__info">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12 movie__info__info__rating ">
                            <p class="h1 text-center movie__rating__value"><strong>${movieDTO.averageRating}/100</strong></p>
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
                                <#list movieDTO.participants as participant>
                                    <li>
                                        <a href="/person?id=${participant.id}" class="link__grey">
                                            ${participant.firstName} ${participant.lastName}
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row movie__description__row">
                <div class="col-md-10 movie__description">
                    <p class="h3">Обзор</p>
                    <p>${movieDTO.description}</p>
                </div>
            </div>

            <div class="row movie__comments__row">
                <div class="col-md-10 offset-md-1 movie__comments">
                    <div class="movie__comments__form__wrapper">
                        <#if authorized??>
                            <p class="h5 movie__comments__header"><strong>Оставить комментарий</strong></p>
                            <form method="post" action="/movieCommentary">
                                <div class="form-group">
                                    <label for="comment_text">Фильм</label>
                                    <input class="form-control" type="text" name="movieId" value="${movieDTO.id}"  readonly>
                                    <label for="comment_text">Пользователь</label>
                                    <input class="form-control" type="text" name="ownerId" value="${userDTO.id}"  readonly>
                                    <label for="comment_text">Текст комментария</label>
                                    <textarea name="text" class="form-control .form-control-lg" id="comment_text" rows="3"></textarea>
                                    <small id="emailHelp" class="form-text text-muted">не более 200 символов</small>
                                </div>
                                <br>
                                <button type="submit" class="btn ">Оставить комментарий</button>
                            </form>
                        <#else>
                            <p class="h5 movie__comments__header"><strong>Комментарии могут оставлять только авторизованные пользователи</strong></p>
                        </#if>
                    </div>

                    <div class="movie__comments__header__wrapper">
                        <p class="h3 movie__comments__header"><strong>Комментарии <sup>${movieDTO.movieCommentaryDTOList?size}</sup></strong></p>
                    </div>
                    <div class="movie__comments__list__wrapper">
                        <ul class="list-unstyled movie__comments__list">
                            <#list movieDTO.movieCommentaryDTOList as comment>
                                <li>
                                    <div class="movie__comments__list__elem">
                                        <p>
                                            <span class="movie__comment__created_at">${comment.createdAt}</span>
                                            <span class="movie__comment__username">
                                                <a href="/profile?username=${comment.ownerUsername}" class="link__grey">
                                                    ${comment.ownerUsername}
                                                </a>:
                                            </span>
                                        </p>
                                        <p class="movie__comment__text">${comment.text}</p>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>