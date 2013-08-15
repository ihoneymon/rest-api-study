/**
 * jsRender Template 생성 메소드
 * @param $target 랜더링된 HTML요소를 붙일 대상
 * @param templateId 랜더링용 템플릿ID
 * @param data 랜더링에 사용될 데이터
 * @param isClear 랜더링 대상에 대한 처리
 */
var renderTemplate = function ($target, templateId, data, isClear) {
    console.log("renderTemplate : {ID : " + $target.prop("id") + ", Template ID : " + templateId + ", Data size: " + data.size + ", isClear : " + isClear + "}");
    if (isClear) {
        $target.empty();
    }

    var targetTemplate = $.templates(templateId);
    var templateOutput = targetTemplate.render(data);
    $target.append(templateOutput);
};