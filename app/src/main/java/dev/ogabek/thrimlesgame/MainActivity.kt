package dev.ogabek.thrimlesgame

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.animation.addListener
import dev.ogabek.thrimlesgame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var randomNumber: Int = 0

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() {

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.apply {
            btnPlay.setOnClickListener {
                llThirdPole.visibility = View.INVISIBLE

                randomNumber = (1..3).random()

                when (randomNumber) {
                    1 -> {
                        ivFirstBall.visibility = View.VISIBLE
                        ivSecondBall.visibility = View.INVISIBLE
                        ivThirdBall.visibility = View.INVISIBLE
                        open(ivFirstBox, true, isLose = false, winImage = null)
                    }
                    2 -> {
                        ivFirstBall.visibility = View.INVISIBLE
                        ivSecondBall.visibility = View.VISIBLE
                        ivThirdBall.visibility = View.INVISIBLE
                        open(ivSecondBox, true, isLose = false, winImage = null)
                    }
                    3 -> {
                        ivFirstBall.visibility = View.INVISIBLE
                        ivSecondBall.visibility = View.INVISIBLE
                        ivThirdBall.visibility = View.VISIBLE
                        open(ivThirdBox, true, isLose = false, winImage = null)
                    }
                }

            }
        }

    }

    private fun open(image: ImageView, isNewGame: Boolean, isLose: Boolean, winImage: ImageView?) {
        ObjectAnimator.ofFloat(image, "translationY", -175F).apply {
            duration = 125
            start()
        }.addListener({
            Thread.sleep(1000)
            ObjectAnimator.ofFloat(image, "translationY", 0F).apply {
                duration = 125
                start()
            }.addListener({
                if (isNewGame) startGame()
                if (isLose) open(winImage!!, isNewGame = false, isLose = false, winImage = winImage)
            })
        })

    }

    @SuppressLint("SetTextI18n")
    private fun startGame() {

        binding.llFirst.isEnabled = false
        binding.llSecond.isEnabled = false
        binding.llThird.isEnabled = false

        setFirst()
        setThird()
        setSecond()

        randomNumber = (1..3).random()

        binding.apply {
            when (randomNumber) {
                1 -> {
                    ivFirstBall.visibility = View.VISIBLE
                    ivSecondBall.visibility = View.INVISIBLE
                    ivThirdBall.visibility = View.INVISIBLE
                }
                2 -> {
                    ivFirstBall.visibility = View.INVISIBLE
                    ivSecondBall.visibility = View.VISIBLE
                    ivThirdBall.visibility = View.INVISIBLE
                }
                3 -> {
                    ivFirstBall.visibility = View.INVISIBLE
                    ivSecondBall.visibility = View.INVISIBLE
                    ivThirdBall.visibility = View.VISIBLE
                }
            }
        }

        binding.apply {
            llFirst.setOnClickListener {

                val winImage: ImageView? = when (randomNumber) {
                    1 -> {
                        binding.ivFirstBox
                    }
                    2 -> {
                        binding.ivSecondBox
                    }
                    3 -> {
                        binding.ivThirdBox
                    }
                    else -> null
                }

                val win = randomNumber == 1

                open(ivFirstBox, false, isLose = !win, winImage)

                if (win) {
                    Toast.makeText(this@MainActivity, "You win", Toast.LENGTH_SHORT).show()
                    score += 1
                    tvScore.text = "Score : $score"
                } else Toast.makeText(this@MainActivity, "You Lose", Toast.LENGTH_SHORT).show()
                llThirdPole.visibility = View.VISIBLE
                btnPlay.text = "Play again"

                binding.llFirst.isEnabled = false
                binding.llSecond.isEnabled = false
                binding.llThird.isEnabled = false

            }

            llSecond.setOnClickListener {
                val winImage: ImageView? = when (randomNumber) {
                    1 -> {
                        binding.ivFirstBox
                    }
                    2 -> {
                        binding.ivSecondBox
                    }
                    3 -> {
                        binding.ivThirdBox
                    }
                    else -> null
                }

                val win = randomNumber == 2

                open(ivSecondBox, false, isLose = !win, winImage)

                if (win) {
                    Toast.makeText(this@MainActivity, "You win", Toast.LENGTH_SHORT).show()
                    score += 1
                    tvScore.text = "Score : $score"
                } else Toast.makeText(this@MainActivity, "You Lose", Toast.LENGTH_SHORT).show()
                llThirdPole.visibility = View.VISIBLE
                btnPlay.text = "Play again"

                binding.llFirst.isEnabled = false
                binding.llSecond.isEnabled = false
                binding.llThird.isEnabled = false

            }

            llThird.setOnClickListener {
                val winImage: ImageView? = when (randomNumber) {
                    1 -> {
                        binding.ivFirstBox
                    }
                    2 -> {
                        binding.ivSecondBox
                    }
                    3 -> {
                        binding.ivThirdBox
                    }
                    else -> null
                }

                val win = randomNumber == 3

                open(ivThirdBox, false, isLose = !win, winImage)

                if (win) {
                    Toast.makeText(this@MainActivity, "You win", Toast.LENGTH_SHORT).show()
                    score += 1
                    tvScore.text = "Score : $score"
                } else Toast.makeText(this@MainActivity, "You Lose", Toast.LENGTH_SHORT).show()
                llThirdPole.visibility = View.VISIBLE
                btnPlay.text = "Play again"

                binding.llFirst.isEnabled = false
                binding.llSecond.isEnabled = false
                binding.llThird.isEnabled = false

            }

        }

    }

    private fun setFirst() {
        ObjectAnimator.ofFloat(binding.llFirst, "translationX", (500..700).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.llFirst, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.llFirst,
                        "translationX",
                        (500..700).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.llFirst, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.llFirst,
                                "translationX",
                                (500..700).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.llFirst, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.llFirst,
                                        "translationX",
                                        (500..700).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.llFirst, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.llFirst,
                                                    "translationX",
                                                    (500..700).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.llFirst,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.llFirst,
                                                            "translationX",
                                                            (500..700).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.llFirst,
                                                                "translationX",
                                                                0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.llFirst,
                                                                    "translationX",
                                                                    (500..700).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.llFirst,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.llFirst,
                                                                            "translationX",
                                                                            (500..700).random().toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.llFirst,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.llFirst.isEnabled =
                                                                                    true
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }

    private fun setThird() {
        ObjectAnimator.ofFloat(binding.llThird, "translationX", (-700..-500).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.llThird, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.llThird,
                        "translationX",
                        (-700..-500).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.llThird, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.llThird,
                                "translationX",
                                (-700..-500).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.llThird, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.llThird,
                                        "translationX",
                                        (-700..-500).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.llThird, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.llThird,
                                                    "translationX",
                                                    (-700..-500).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.llThird,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.llThird,
                                                            "translationX",
                                                            (-700..-500).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.llThird,
                                                                "translationX",
                                                                0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.llThird,
                                                                    "translationX",
                                                                    (-700..-500).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.llThird,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.llThird,
                                                                            "translationX",
                                                                            (-700..-500).random().toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.llThird,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.llThird.isEnabled =
                                                                                    true
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }

    private fun setSecond() {
        ObjectAnimator.ofFloat(binding.llSecond, "translationX", (-500..500).random().toFloat())
            .apply {
                duration = 600
                start()
            }.addListener({
                ObjectAnimator.ofFloat(binding.llSecond, "translationX", 0F).apply {
                    duration = 600
                    start()
                }.addListener({
                    ObjectAnimator.ofFloat(
                        binding.llSecond,
                        "translationX",
                        (-500..500).random().toFloat()
                    ).apply {
                        duration = 600
                        start()
                    }.addListener({
                        ObjectAnimator.ofFloat(binding.llSecond, "translationX", 0F).apply {
                            duration = 500
                            start()
                        }.addListener({
                            ObjectAnimator.ofFloat(
                                binding.llSecond,
                                "translationX",
                                (-500..500).random().toFloat()
                            ).apply {
                                duration = 500
                                start()
                            }.addListener({
                                ObjectAnimator.ofFloat(binding.llSecond, "translationX", 0F).apply {
                                    duration = 500
                                    start()
                                }.addListener({
                                    ObjectAnimator.ofFloat(
                                        binding.llSecond,
                                        "translationX",
                                        (-500..500).random().toFloat()
                                    ).apply {
                                        duration = 500
                                        start()
                                    }.addListener({
                                        ObjectAnimator.ofFloat(binding.llSecond, "translationX", 0F)
                                            .apply {
                                                duration = 400
                                                start()
                                            }.addListener({
                                                ObjectAnimator.ofFloat(
                                                    binding.llSecond,
                                                    "translationX",
                                                    (-500..500).random().toFloat()
                                                ).apply {
                                                    duration = 400
                                                    start()
                                                }.addListener({
                                                    ObjectAnimator.ofFloat(
                                                        binding.llSecond,
                                                        "translationX",
                                                        0F
                                                    ).apply {
                                                        duration = 400
                                                        start()
                                                    }.addListener({
                                                        ObjectAnimator.ofFloat(
                                                            binding.llSecond,
                                                            "translationX",
                                                            (-500..500).random().toFloat()
                                                        ).apply {
                                                            duration = 400
                                                            start()
                                                        }.addListener({
                                                            ObjectAnimator.ofFloat(
                                                                binding.llSecond,
                                                                "translationX",
                                                                0F
                                                            ).apply {
                                                                duration = 300
                                                                start()
                                                            }.addListener({
                                                                ObjectAnimator.ofFloat(
                                                                    binding.llSecond,
                                                                    "translationX",
                                                                    (-500..500).random().toFloat()
                                                                ).apply {
                                                                    duration = 300
                                                                    start()
                                                                }.addListener({
                                                                    ObjectAnimator.ofFloat(
                                                                        binding.llSecond,
                                                                        "translationX",
                                                                        0F
                                                                    ).apply {
                                                                        duration = 300
                                                                        start()
                                                                    }.addListener({
                                                                        ObjectAnimator.ofFloat(
                                                                            binding.llSecond,
                                                                            "translationX",
                                                                            (-500..500).random().toFloat()
                                                                        ).apply {
                                                                            duration = 300
                                                                            start()
                                                                        }.addListener({
                                                                            ObjectAnimator.ofFloat(
                                                                                binding.llSecond,
                                                                                "translationX",
                                                                                0F
                                                                            ).apply {
                                                                                duration = 300
                                                                                start()
                                                                            }.addListener({
                                                                                binding.llSecond.isEnabled =
                                                                                    true
                                                                            })
                                                                        })
                                                                    })
                                                                })
                                                            })
                                                        })
                                                    })
                                                })
                                            })
                                    })
                                })
                            })
                        })
                    })
                })
            })
    }

}

fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start