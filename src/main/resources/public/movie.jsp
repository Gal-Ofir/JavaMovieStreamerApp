<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <style>
        body {
            font-family: 'Gloria Hallelujah', cursive;
            height: 100%;
            margin: 0;
            padding: 0;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            background: #83a4d4; /* fallback for old browsers */
            background: -webkit-linear-gradient(to right, #b6fbff, #83a4d4); /* Chrome 10-25, Safari 5.1-6 */
            background: linear-gradient(to right, #b6fbff, #83a4d4); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
            height: -webkit-fill-available;
            text-align: center;
        }
    </style>
    <title>
        Java Movie Streamer
    </title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

</head>
<body>
<div>
    <div>
        <h1>Spring Boot Java movie Streamer</h1>
        <h2>${movieName}</h2>
        <h3><a href="/">Back</a></h3>
        <div>

            <video width="320" height="240" autoplay="autoplay" controls>
                <source src="/movies/play/${fullMovieName}" type="video/mp4">
            </video>

        </div>

    </div>
</div>
</body>
</html>