<#ftl encoding='UTF-8'>
<#include "base.ftl">
<#macro title>Sign up</#macro>
<#macro main>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <br>
                <form action="/signUp" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" name="email">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First name">
                            </div>
                            <div class="col-md-6">
                                <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last name">
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="dateOfBirth" class="form-label">Date of birth</label>
                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth">
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Bio</label>
                        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary">Sign in</button>
                </form>
            </div>
        </div>
    </div>
</#macro>