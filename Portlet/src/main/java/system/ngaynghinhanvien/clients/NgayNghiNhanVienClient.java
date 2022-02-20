package system.ngaynghinhanvien.clients;

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
import system.ngaynghinhanvien.dto.NgayNghiNhanVienDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NgayNghiNhanVienClient {
    private final String path = "";
    @Autowired
    @Qualifier("ngayNghiNhanVienRest")
    public RestTemplate restTemplate;
    @Autowired
    @Qualifier("objectMapper")
    public ObjectMapper objectMapper;

    public NgayNghiNhanVienDTO getById(Integer id) {
        String restURL = path + "/" + id;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<NgayNghiNhanVienDTO> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<NgayNghiNhanVienDTO>() {
                });
        return responseEntity.getBody();
    }

    public List<NgayNghiNhanVienDTO> gets() {
        String restURL = path;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<NgayNghiNhanVienDTO>>() {
            });
        } catch (Exception e) {
            return null;
        }
    }

    public boolean add(NgayNghiNhanVienDTO ngayNghiNhanVienDTO) {

        String restURL = path + "/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        HttpEntity<NgayNghiNhanVienDTO> entity = new HttpEntity<>(ngayNghiNhanVienDTO, headers);
        System.out.println(ngayNghiNhanVienDTO.toString());

        HttpEntity<?> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
                entity, String.class);

        return ((ResponseEntity<?>) response).getStatusCode().is2xxSuccessful();
    }
    public List<NgayNghiNhanVienDTO> getNNNVByUserId(Long userId) {
        String restURL = path + "/user/" + userId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(restURL);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, null, String.class);
        try {
            return objectMapper.readValue(responseEntity.getBody(),
                    new TypeReference<List<NgayNghiNhanVienDTO>>() {
                    });
        } catch (Exception e) {
            return null;
        }
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
