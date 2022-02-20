package system.ot.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import system.ot.dto.OTDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OTClient {
    private final String path = "";
    @Autowired
    @Qualifier("oTRest")
    public RestTemplate restTemplate;
    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public OTDTO getOT(Integer id) {
        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<OTDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<OTDTO>() {
                });
        return responseEntity.getBody();
    }

    public List<OTDTO> getOTs() {
        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<OTDTO>>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addOT(OTDTO otdto) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<OTDTO> entity = new HttpEntity<>(otdto, headers);
        System.out.println(otdto.toString());

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean deleteOT(Integer id) {
        String restURL = path + "/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }

    public List<OTDTO> getOTByUserId(Long userId) {
        String restURL = path + "/nhanvien/" + userId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<OTDTO>>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

    public boolean acceptOT(Integer id) {
        String restURL = path + "/accept/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean denyOT(Integer id) {
        String restURL = path + "/deny/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public List<OTDTO> getByDuAnId(Integer id) {
        String restURL = path + "/duan/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<OTDTO>>() {
            });
        } catch (Exception e) {
            return null;
        }
    }
}
