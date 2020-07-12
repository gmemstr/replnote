(ns replnote.editor-test
  (:require [clojure.test :refer :all]
            [replnote.editor :refer :all]))

(deftest eval-string-test
		(reset! debug false)
  (testing "eval-str"
    (is (= (eval-str "(+ 1 1)") "2"))))

(deftest toggle-debug-test
	(reset! debug false)
	(testing "toggle-debug"
		(is (= true (toggle-debug))))
)

(deftest debug-eval-string-test
	(reset! debug true)
	(testing "eval-str with debug"
		(is (= (eval-str "(+ 1 1)") "[2](+ 1 1)")))
)

(deftest replace-things-test
	(reset! debug false)
	(testing "replace-things"
		(is (= (replace-things "This #(str \"x\").")) "This x."))
)

(deftest debug-replace-things-test
	(reset! debug true)
	(testing "replace-things with debug"
		(is (= (replace-things "This #(str \"x\").")) "This [x](str \"x\")."))
)
