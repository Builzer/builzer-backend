package com.builzer.backend.project.adapter.out.persistence

import com.builzer.backend.project.adapter.out.SupportedLanguagePersistenceAdaptor
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageProvider
import com.builzer.backend.project.adapter.out.persistence.entity.enums.LanguageType
import com.builzer.backend.project.adapter.out.persistence.repository.SupportedLanguageJpaRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@ActiveProfiles("test")
@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SupportedLanguageJpaRepositoryTest(private val supportedLanguageJpaRepository: SupportedLanguageJpaRepository)
    : BehaviorSpec({

    Given("언어종류, 언어버전, 언어제공자로") {
        val languageType = LanguageType.JAVA
        val languageVersion = 11F
        val languageProvider = LanguageProvider.OPEN_JDK

        When("read를 하면") {
            val supportedLanguage = supportedLanguageJpaRepository
                    .find(languageType = languageType,
                            languageVersion = languageVersion,
                            languageProvider = languageProvider)!!

            Then("해당하는 지원 언어를 가져올 수 있다.") {
                supportedLanguage.id shouldNotBe null
                supportedLanguage.languageType shouldBe languageType
                supportedLanguage.languageVersion shouldBe languageVersion
                supportedLanguage.languageProvider shouldBe languageProvider
            }
        }
    }
})