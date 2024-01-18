package org.hunzz.todoapplication.global.util

object NicknameGenerator {
    private val adjectives =
        listOf(
            "갸냘픈", "거센", "게으른", "건방진", "고달픈", "너그러운", "더러운", "뜨거운", "못생긴", "배고픈",
            "아름다운", "아픈", "약한", "잘생긴", "재미있는", "즐거운", "짓궂은", "탐욕스러운", "탐스러운", "힘찬"
        )
    private val animals =
        listOf(
            "강아지", "개구리", "고양이", "공룡", "노루", "돌고래", "멧돼지", "백호", "병아리", "사슴",
            "사자", "새우", "송아지", "얼룩말", "올챙이", "원숭이", "지렁이", "참새", "해파리", "호랑이"
        )

    fun generateNickname() = "${adjectives.random()} ${animals.random()}"
}