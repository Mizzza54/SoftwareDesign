package ru.ifmo.gerasimov.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ifmo.gerasimov.dto.ResponseTo;
import ru.ifmo.gerasimov.exception.VkNewsfeedException;

import java.net.URI;
import java.util.Optional;

/**
 * @author Michael Gerasimov
 */

public class VkNewsfeedClient {
    private final static int ZERO_NUMBER_RECORDS_RETURNED = 0;

    private final String vkNewsfeedApiUrl;
    private final RestTemplate restTemplate;
    private final String accessToken;
    private final String apiVersion;

    public VkNewsfeedClient(String vkNewsfeedApiUrl, String accessToken, String apiVersion, RestTemplate restTemplate) {
        this.vkNewsfeedApiUrl = vkNewsfeedApiUrl;
        this.accessToken = accessToken;
        this.apiVersion = apiVersion;
        this.restTemplate = restTemplate;
    }

    public Optional<Integer> retrieveNewsfeed(String query, long startTime, long endTime) {
        URI uri = UriComponentsBuilder.fromUriString(vkNewsfeedApiUrl)
                .pathSegment("newsfeed.search")
                .queryParam("v", apiVersion)
                .queryParam("access_token", accessToken)
                .queryParam("q",  query)
                .queryParam("start_time", startTime)
                .queryParam("end_time", endTime)
                .queryParam("count", ZERO_NUMBER_RECORDS_RETURNED)
                .build()
                .toUri();

        ResponseTo response = restTemplate.getForObject(uri, ResponseTo.class);

        if (response == null) {
            throw new VkNewsfeedException("Error: Response is null");
        }

        if (response.getSuccess() == null) {
            throw new VkNewsfeedException(
                    String.format(
                            "Error: code = %d, message = %s",
                            response.getError().getErrorCode(),
                            response.getError().getErrorMessage()
                    )
            );
        }

        return Optional.of(response.getSuccess().getTotalCount());
    }
}
