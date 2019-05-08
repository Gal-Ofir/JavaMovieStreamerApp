$(function () {

    $.ajax({
        url: '/movies/list/',
        dataType: 'json'
    })
        .done(function (data) {

            data.forEach(function (movie) {
                $("#movie-table")
                    .append(`<tr> 
                                  <td>
                                    <a href=/${movie.fullMovieName}>${movie.name}</a>
                                  </td>
                             </tr>`);
            });

        });

});