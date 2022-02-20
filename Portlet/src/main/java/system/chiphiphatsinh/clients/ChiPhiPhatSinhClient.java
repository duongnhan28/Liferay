package system.chiphiphatsinh.clients;

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
import system.chiphiphatsinh.dto.ChiPhiPhatSinhDTO;
import system.nhanvien.dto.NhanVienDTO;
import system.ot.dto.OTDTO;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiPhiPhatSinhClient {

    @Autowired
    @Qualifier("chiPhiPhatSinhRest")
    public RestTemplate restTemplate;

    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    private final String path = "";

    public ChiPhiPhatSinhDTO getChiPhiPhatSinh(Integer id) {

        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<ChiPhiPhatSinhDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<ChiPhiPhatSinhDTO>() {
                });

        return responseEntity.getBody();
    }

    public List<ChiPhiPhatSinhDTO> getChiPhiPhatSinhs() throws IOException {

        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);

        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<ChiPhiPhatSinhDTO>>() {});
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addChiPhiPhatSinh(ChiPhiPhatSinhDTO chiPhiPhatSinhDTO) {

        String restURL = path + "/add";

        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<ChiPhiPhatSinhDTO> entity = new HttpEntity<>(chiPhiPhatSinhDTO, headers);

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class, new Object[0]);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }

    public boolean deleteCPPS(Integer id) {

        String restURL = path + "/delete/" + id;

        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<?> entity = new HttpEntity(headers);
        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE,
                entity, String.class, new Object[0]);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();

    }

    public List<ChiPhiPhatSinhDTO> getCPPSByUserId(Long userId) {
        String restURL = path + "/user/" + userId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<ChiPhiPhatSinhDTO>>() {
            });
        } catch (Exception e) {
            return null;
        }
    }
}