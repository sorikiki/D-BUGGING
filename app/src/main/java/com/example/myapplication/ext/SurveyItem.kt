package com.example.myapplication.ext

data class SurveyItem(
    val id: Int?,
    val question: String?,
    val keyWords: List<String>?
)

val colorItem: SurveyItem = SurveyItem(
    1,
    "어떤 색인가요?",
    listOf("검정색", "짙은 갈색", "옅은 갈색", "붉은색", "흰색", "노란색", "초록색", "은색", "세로 줄무늬", "회색", "파란색", "점 무늬")
)

val movementItem: SurveyItem = SurveyItem(
    2,
    "움직임은 어땠나요?",
    listOf("날아다님", "기어다님", "느림", "빠름", "뛰어오름", "무리 지음", "혼자 다님")
)

val traitItem: SurveyItem = SurveyItem(
    3,
    "어떤 특징이 있나요?",
    listOf(
        "긴 더듬이",
        "짧은 더듬이",
        "큰 날개",
        "작은 날개",
        "다리 6개",
        "다리 8개",
        "다리 많음",
        "큰 뒷다리",
        "좁쌀 크기",
        "먼지 크기",
        "손톱 크기",
        "지우개 크기",
        "털 있음",
        "소리 남",
        "사람 공격",
        "긴 몸통",
        "몸통 마디",
        "20cm"
    )
)

val conditionItem: SurveyItem = SurveyItem(
    4,
    "방의 상태는 어땠나요?",
    listOf(
        "먼지 많음",
        "습함",
        "깔끔함",
        "건조함",
        "청소 자주함",
        "청소 오래됨",
        "어두움",
        "밝음",
        "관리 안됨",
        "잦은 환기",
        "환기 부족",
        "이사함",
        "특이사항 없음"
    )
)
