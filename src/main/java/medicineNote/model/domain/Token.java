package medicineNote.model.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Token {
	private int id;
	private String token;
	private Timestamp createdAt;
	private Timestamp updateAt;
}
