$(function () {

    /*
     * Slideshow
     */
    $('.slideshow').each(function () {

    // 變數準備
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        var $container = $(this),                                 // a
            $slideGroup = $container.find('.slideshow-slides'),   // b
            $slides = $slideGroup.find('.slide'),                 // c
            $nav = $container.find('.slideshow-nav'),             // d
            $indicator = $container.find('.slideshow-indicator'), // e
            // SlideShow內各元素的jQuery物件
            // a SlideShow整體的container
            // b 所有Slide的彙整(SlideGroup)
            // c 各個Slide
            // d 瀏覽鏈結(Prev/Next)
            // e 指標鏈結(Dot)

            slideCount = $slides.length, // Slide的個數
            indicatorHTML = '',          // 指標鏈結的內容
            currentIndex = 0,            // 目前Slide的索引值
            duration = 500,              // 轉換至下個Slide所需的動畫時間
            easing = 'easeInOutExpo',    // 轉換至下個Slide的easing種類
            interval = 7500,             // 自動切換的時間
            timer;                       // 用以儲存timer


    // HTML元素的配置、建立與插入
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // 決定各個Slide的位置
        // 並建立對應的指標鏈結
        $slides.each(function (i) {
            $(this).css({ left: 100 * i + '%' });
            indicatorHTML += '<a href="#">' + (i + 1) + '</a>';
        });

        // 插入指標鏈結的內容
        $indicator.html(indicatorHTML);


    // 函數定義
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // 用以顯示任意Slide的函數
        function goToSlide (index) {
            // SlideGroup配合目標位置移動
            $slideGroup.animate({ left: - 100 * index + '%' }, duration, easing);
            // 記錄當前Slide的索引值
            currentIndex = index;
            // 更新瀏覽鏈結、指標鏈結的狀態
            updateNav();
        }

        // 依照Slide狀態更新更新瀏覽鏈結、指標鏈結的函數
        function updateNav () {
            var $navPrev = $nav.find('.prev'), // Prev (前進) 鏈結
                $navNext = $nav.find('.next'); // Next (後退) 鏈結
            // 若為第一個Slide，則將Prev瀏覽鏈結設定無效
            if (currentIndex === 0) {
                $navPrev.addClass('disabled');
            } else {
                $navPrev.removeClass('disabled');
            }
            // 若為最後的Slide，則將Next瀏覽鏈結設定無效
            if (currentIndex === slideCount - 1) {
                $navNext.addClass('disabled');
            } else {
                $navNext.removeClass('disabled');
            }
            // 將當前Slide的指標鏈結設定無效
            $indicator.find('a').removeClass('active')
                .eq(currentIndex).addClass('active');
        }

        // 起始timer的函數
        function startTimer () {
            //利用變數interval設定經過多久的時間就執行處理 
            timer = setInterval(function () {
                // 以當前Slide的索引值決定下個要顯示的Slide
                // 若為最後的Slide則顯示第一張Slide
                var nextIndex = (currentIndex + 1) % slideCount;
                goToSlide(nextIndex);
            }, interval);
        }

        // 停止tismer的函數
        function stopTimer () {
            clearInterval(timer);
        }


    // 事件註冊
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // 點擊瀏覽鏈結後顯示對應的Slide
        $nav.on('click', 'a', function (event) {
            event.preventDefault();
            if ($(this).hasClass('prev')) {
                goToSlide(currentIndex - 1);
            } else {
                goToSlide(currentIndex + 1);
            }
        });

        // 點擊指標鏈結後顯示對應的Slide
        $indicator.on('click', 'a', function (event) {
            event.preventDefault();
            if (!$(this).hasClass('active')) {
                goToSlide($(this).index());
            }
        });

        // 滑鼠移入時暫停timer，移出則啟動timer
        $container.on({
            mouseenter: stopTimer,
            mouseleave: startTimer
        });


    // 啟動SlideShow
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // 顯示最初的Slide
        goToSlide(currentIndex);

        // 啟動timer
        startTimer();

    });

});
