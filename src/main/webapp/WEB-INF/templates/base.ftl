<!doctype html>
<html lang="en">
<head>
    <title><@title></@title> - MovieSearcherApp</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS v5.0.2 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"  integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
<header>
    <div class="header">
        <div class="container-fluid">
            <div class="row header__content">
                <div class="col-md-3 p-3 border bg-light header__item">
                </div>
                <div class="col-md-3  p-3 border bg-light header__item">
                </div>
                <div class="col-md-3  p-3 border bg-light header__item">
                </div>
                <div class="col-md-3  p-3 border bg-light header__item">
                </div>
            </div>
        </div>
    </div>

    <nav id="navbar" class="navbar navbar-expand-lg navbar-dark header__navbar">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="/static/img/logo.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
                MovieSearcherApp
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/genres">Категории</a>
                    </li>
                </ul>
            </div>
            <div class="d-flex">
                <#if Session.lightUserDTO??>
                    <a href="/profile?username=${lightUserDTO.username}">
                        <img class="rounded-circle" height="64" width="64" src="${lightUserDTO.imageUrl}" alt="profile image">
                    </a>
                <#else>
                    <a href="/signIn">
                        <button class="btn btn-outline-warning">Sign in</button>
                    </a>
                </#if>
            </div>
        </div>
    </nav>
</header>
<main>
    <@main></@main>
</main>
<footer>

</footer>
<!-- ajax google cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- Bootstrap JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="/static/js/style.js"></script>
<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')})
</script>
<script>
    $(document).ready(function(){
        $('.review__selector').click(function(event){
            $("#exampleModal").modal('show');
            event.preventDefault();
            $.ajax({
                url:'/review?' + event.target.id,
                type:'GET',
                dataType: 'json',
                success: function(data) {
                    $("#modal-review-movieName").html(data['movieName']);
                    $("#modal-review-movieName").attr('href', '/movie/' + data['movieId']);
                    $("#modal-review-rating").html(data['rating'] + '/100');
                    $("#modal-review-text").html(data['description']);
                },
                error: function(){
                    $(".modal-body").html('Error');
                }
            })
        })
    })
</script>
</body>
</html>