<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Sign in</#macro>
<#macro main>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <br>
                <form action="/signIn" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <button type="submit" class="btn btn-outline-secondary">Sign in</button>
                </form>
                <div class="form-text">Нет аккаунта? <a href="/signUp" class="link__grey">Зарегистрироваться</a></div>
            </div>
        </div>
    </div>
</#macro>