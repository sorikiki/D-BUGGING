package com.example.myapplication.ext

data class SurveyItem(
    val id: Int?,
    val question: String?,
    val keyWords: List<Trait>?
)

data class Trait(
    val query: String?,
    var isSelected: Boolean? = false
)

val colorItem: SurveyItem = SurveyItem(
    1,
    "어떤 색인가요?",
    listOf(
        Trait("검정색"),
        Trait("짙은 갈색"),
        Trait("옅은 갈색"),
        Trait("붉은색"),
        Trait("흰색"),
        Trait("노란색"),
        Trait("초록색"),
        Trait("은색"),
        Trait("세로 줄무늬"),
        Trait("세로 줄무늬"),
        Trait("회색"),
        Trait("파란색"),
        Trait("점 무늬")
    )
)

val movementItem: SurveyItem = SurveyItem(
    2,
    "움직임은 어땠나요?",
    listOf(
        Trait("날아다님"),
        Trait("기어다님"),
        Trait("느림"),
        Trait("빠름"),
        Trait("뛰어오름"),
        Trait("무리 지음"),
        Trait("혼자 다님")
    )
)

val traitItem: SurveyItem = SurveyItem(
    3,
    "어떤 특징이 있나요?",
    listOf(
        Trait("긴 더듬이"),
        Trait("짧은 더듬이"),
        Trait("큰 날개"),
        Trait("작은 날개"),
        Trait("다리 6개"),
        Trait("다리 8개"),
        Trait("다리 많음"),
        Trait("큰 뒷다리"),
        Trait("좁쌀 크기"),
        Trait("먼지 크기"),
        Trait("손톱 크기"),
        Trait("지우개 크기"),
        Trait("털 있음"),
        Trait("소리 남"),
        Trait("사람 공격"),
        Trait("긴 몸통"),
        Trait("몸통 마디"),
        Trait("20cm")
    )
)

val conditionItem: SurveyItem = SurveyItem(
    4,
    "방의 상태는 어땠나요?",
    listOf(
        Trait("먼지 많음"),
        Trait("습함"),
        Trait("깔끔함"),
        Trait("건조함"),
        Trait("청소 자주함"),
        Trait("청소 오래됨"),
        Trait("어두움"),
        Trait("밝음"),
        Trait("관리 안됨"),
        Trait("잦은 환기"),
        Trait("환기 부족"),
        Trait("이사함"),
        Trait("특이사항 없음")
    )
)
