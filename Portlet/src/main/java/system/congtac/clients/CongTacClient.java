package system.congtac.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import system.congtac.dto.CongTacDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CongTacClient {

    private final String path = "";

    @Autowired
    @Qualifier("congTacRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public CongTacDTO getCongTac(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<CongTacDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<CongTacDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<CongTacDTO> getCongTacs() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<CongTacDTO>>() {
            });
        } catch (Exception e) {

            return null;
        }
    }

    public List<CongTacDTO> getCongTacByDuAn(Integer id) {

        String restURL = path + "/duan/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null , String.class);

        try {

            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<CongTacDTO>>() {});
        } catch (Exception e) {

            return null;
        }
    }

    public boolean addCongTac(CongTacDTO duAnDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<CongTacDTO> entity = new HttpEntity<>(duAnDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }

    public boolean delete(Integer id) {

        String restURL = path + "/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }
}
