package ofir.gal.app.movie;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Controller
@ComponentScan("ofir.gal.app.movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @SneakyThrows
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        movieService.addMovieToDirectory(file.getOriginalFilename(), file.getInputStream());

        return "redirect:/";
    }

    @GetMapping(value = "/movies/list", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Movie>> listMovies() {
        List<Movie> movies = movieService.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);

    }

    @GetMapping(value = "/{fullMovieName}")
    public String toMoviePage(Map<String, Object> model, @PathVariable String fullMovieName) {
        model.put("movieName", fullMovieName.split("\\.")[0]);
        model.put("fullMovieName", fullMovieName);
        return "movie";
    }

    @GetMapping(value = "/movies/play/{movieName}")
    @ResponseBody
    @SneakyThrows
    public ResponseEntity<ResourceRegion> getMovieStream(@PathVariable String movieName, @RequestHeader HttpHeaders httpHeaders) {

        // get movie resource
        UrlResource movieResource = new UrlResource("file:///" + movieService.getMovieByName(movieName).getAbsolutePathToMovie());

        // get the movie's resources array buffer in the range (region) we need, according the ranges we got from the http headers
        ResourceRegion region = getResourceRegion(movieResource, httpHeaders);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(movieResource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    @SneakyThrows
    private ResourceRegion getResourceRegion(UrlResource movieResource, HttpHeaders httpHeaders) {
        long contentLength = movieResource.contentLength();
        HttpRange range = httpHeaders.getRange().size() == 0 ? null : httpHeaders.getRange().get(0);
        if (range != null) {
            long start = range.getRangeStart(contentLength);
            long end = range.getRangeEnd(contentLength);
            long rangeLength = Math.min(1000000L, end - start + 1);
            return new ResourceRegion(movieResource, start, rangeLength);
        } else {
            long rangeLength = Math.min(1000000L, contentLength);
            return new ResourceRegion(movieResource, 0, rangeLength);
        }
    }

}
