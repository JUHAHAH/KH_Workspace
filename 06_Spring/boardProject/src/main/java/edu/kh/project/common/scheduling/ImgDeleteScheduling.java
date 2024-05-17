package edu.kh.project.common.scheduling;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.project.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
* @Scheduled
*
* * Spring에서 제공하는 스케줄러 - 스케줄러 : 시간에 따른 특정 작업(Job)의 순서를 지정하는 방법.
*
* 설정 방법
* 1) XXXSSAPPlication.java 파일에 @EnableScheduling 어노테이션 추가
* 2) 스케쥴링 동작을 위한 클래스 작성
*
*
* @Scheduled 속성
*  - fixedDelay : 이전 작업이 끝난 시점으로 부터 고정된 시간(ms)을 설정.
*    @Scheduled(fixedDelay = 10000) // 이전 작업이 끝난 후 10초 뒤에 실행
*   
*  - fixedRate : 이전 작업이 수행되기 시작한 시점으로 부터 고정된 시간(ms)을 설정.
*    @Scheduled(fixedRate  = 10000) // 이전 작업이 시작된 후 10초 뒤에 실행
*   
*   
* * cron 속성 : UNIX계열 잡 스케쥴러 표현식으로 작성 - cron="초 분 시 일 월 요일 [년도]" - 요일 : 1(SUN) ~ 7(SAT)
* ex) 2019년 9월 16일 월요일 10시 30분 20초 cron="20 30 10 16 9 2 " // 연도 생략 가능
*
* - 특수문자
* * : 모든 수.
* - : 두 수 사이의 값. ex) 10-15 -> 10이상 15이하
* , : 특정 값 지정. ex) 3,4,7 -> 3,4,7 지정
* / : 값의 증가. ex) 0/5 -> 0부터 시작하여 5마다
* ? : 특별한 값이 없음. (월, 요일만 해당)
* L : 마지막. (월, 요일만 해당)
* @Scheduled(cron="0 * * * * *") // 모든 0초 마다 -> 매 분마다 실행
*
*
* * 주의사항 - @Scheduled 어노테이션은 매개변수가 없는 메소드에만 적용 가능.
*
*/

@Slf4j
@Component // 단순 빈 등록
@PropertySource("classpath:/config.properties")
@RequiredArgsConstructor
public class ImgDeleteScheduling {

	private final BoardService service;

	// 회원 프로필 이미지 파일 경로
	@Value("${my.profile.folder-path}")
	private String profileFolderPath;

	// 회원 이미지 저장 경로
	@Value("${my.board.folder-path}")
	private String boardFolderPath;

//	@Scheduled(cron = "0 0 * * * *") // 정시마다 수행
//	@Scheduled(cron = "0 0 0 * * *" ) // 자정
//	@Scheduled(cron = "0 0 12 * * *" ) // 정오
	@Scheduled(cron = "0 0 0 1 * *") // 매월 1일
//	@Scheduled(cron = "0,30 * * * * *") // 시계 초단위가 0, 30일 경우 (30초마다)
	public void scheduling() {

		log.info("스케쥴러 동작");

		File boardFolder = new File(boardFolderPath);
		File memberFolder = new File(profileFolderPath);

		// 참조하는 폴더에 존재하는 파일 얻어오기
		File[] boardArr = boardFolder.listFiles();
		File[] memberArr = memberFolder.listFiles();

		// 두 배열을 하나로 합침 (For문 한번만 사용하기 위해)
		// ImageArr 이라는 빈 배열을 boardArr, memberArr 의 길이만큼 생성
		File[] imageArr = new File[boardArr.length + memberArr.length];

		// 배열 내용 복사
		// System.arraycopy(복사할 배열, 시작 위치, 새로운 배열, 새로운 배열의 시작 위치, 끝 위치)
		System.arraycopy(boardArr, 0, imageArr, 0, boardArr.length);
		System.arraycopy(memberArr, 0, imageArr, boardArr.length, memberArr.length);

		// 배열 => 리스트로 변환
		List<File> serverImageList = Arrays.asList(imageArr);

		// 2. DB에 있는 이미지 파일 이름만 모두 조회
		List<File> dbImageList = service.selectDbImageList(serverImageList);

		if (!serverImageList.isEmpty()) {
			for (File serverImage : serverImageList) {
				if (dbImageList.indexOf(serverImage.getName()) == -1) {
					log.info(serverImage.getName() + "삭제");
					serverImage.delete();
				}
			}
		}
	}

}
