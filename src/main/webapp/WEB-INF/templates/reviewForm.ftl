<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Sign in</#macro>
<#macro main>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <h3>Создание рецензии</h3>
                <br>
                <form method="post">
                    <div class="mb-3">
                        <label for="rating" class="form-label">Оценка (от 1 до 100)</label>
                        <input type="number" class="form-control" id="rating" name="rating" min="1" max="100">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Рецензия</label>
                        <textarea class="form-control" id="description" name="description" rows="4"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="movieId" class="form-label">Id фильма</label>
                        <input class="form-control" type="text" id="movieId" name="movieId" value="${movieId}" readonly>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary">Создать</button>
                </form>
            </div>
        </div>
    </div>
</#macro>