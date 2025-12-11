# android-base-architecture (Project Name)

이 프로젝트는 **Clean Architecture**와 **MVI 패턴**을 기반으로 작성된 안드로이드 데모 애플리케이션입니다.
Google의 [Now in Android](https://github.com/android/nowinandroid) 아키텍처를 참고하여, 소규모 프로젝트나 기술 과제에 적합하도록 경량화 및 최적화하였습니다.

## 🛠 Tech Stack & Architecture

### Architecture
- **Clean Architecture**: `app -> domain <- data` 의 의존성 규칙을 준수하여 비즈니스 로직과 UI, 데이터를 분리했습니다.
- **MVI (Model-View-Intent)**: 단방향 데이터 흐름(UDF)을 지향하며, View와 ViewModel(State)의 1:1 매핑 원칙을 준수하여 상태 관리를 명확히 했습니다.
- **Multi-module**: 기능(Feature) 단위로 모듈을 분리하여 확장성과 빌드 속도를 고려했습니다.

### Environment & Tools
- **IDE**: Android Studio Otter 2 Feature Drop | 2025.2.2 이상 권장
- **Build System**: Gradle 8.13
- **Language**: Kotlin

> ⚠️ **주의**: 본 프로젝트는 **Gradle 8.13**을 기반으로 작성되었습니다. 원활한 빌드와 실행을 위해 **Android Studio Otter 2 (2025.2.2)** 버전 혹은 그 상위 버전을 사용하시는 것을 강력히 권장합니다. 하위 버전 사용 시 호환성 문제가 발생할 수 있습니다.

## 🔑 Setup (API Key)

이 프로젝트는 이미지 검색을 위해 **Pixabay API**를 사용합니다. 정상적인 실행을 위해서는 API Key 설정이 필요합니다.

1. [Pixabay API 문서](https://pixabay.com/api/docs/)에서 회원가입 후 API Key를 발급받으세요.
2. 프로젝트 루트 디렉토리의 `local.properties` 파일을 열고(없으면 생성), 아래와 같이 키를 추가해 주세요.
   (`secrets.defaults.properties`에 기본값이 설정되어 있으나, `local.properties`가 우선순위를 가집니다.)

```properties
# local.properties

PIXABAY_API_KEY="발급받은_YOUR_API_KEY_입력"

Note: PIXABAY_API_URL은 기본적으로 설정되어 있으므로 변경이 필요하지 않은 경우 별도로 입력하지 않아도 됩니다.
